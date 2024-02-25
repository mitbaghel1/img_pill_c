package com.ia.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ia.model.PillsCounterVO;
import com.ia.repo.IARepository;

@Service
public class IAService {

	@Value("${file.upload.path}")
	String uploadPath;
	
	@Value("${threshold}")
	String threshold;
	
	@Autowired
	IARepository iaRepository;
	
	public void uploadImgAug()
	{
		try {
            // Command to execute Python script
            String command = "C:\\Program Files\\Python311\\python.exe F:\\Demo_imageCV.py";
            
            // Start the process
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();
            
            // Read output from Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            
            // Wait for the process to finish
            int exitCode = process.waitFor();
            System.out.println("Python script execution finished with exit code: " + exitCode);
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	@Transactional
	public PillsCounterVO saveUploadImg(PillsCounterVO counterVO)
	{
		PillsCounterVO vo=new PillsCounterVO();
		if(counterVO != null)
		{
			MultipartFile file=counterVO.getFile();
			vo.setImgPath(uploadPath+""+file.getOriginalFilename());
			
			try
			{
				    
				 	String fileName = file.getOriginalFilename();
				 	String filePath = uploadPath + File.separator + fileName;		           
		            // Save the file
				 	 File uploadDirectory = new File(uploadPath);
				 	 if (!uploadDirectory.exists()) {
			                boolean mkdirsSuccess = uploadDirectory.mkdirs();
			                if (!mkdirsSuccess) {
			                    throw new RuntimeException("Failed to create upload directory");
			                }
				 	 }
				 	File fileupl=new File(filePath);
				 	file.transferTo(fileupl);
				 	
				 	
				 	//API call for
				 	String apiUrl = "https://testingimage-prediction.cognitiveservices.azure.com/customvision/v3.0/Prediction/345d1e0d-c725-4f4a-a58f-c17a5b0d03c6/detect/iterations/Iteration4/image";
		            String predictionKey = "6b5a975fa95644278b491fa97b9cc8e8";
		            double customThreshold = 0.7;

		            // Encode the image as Base64
		            String base64Image = encodeImageToBase64(fileupl);

		            // Create the HTTP request
		            URL url = new URL(apiUrl);
		            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		            connection.setRequestMethod("POST");
		            connection.setRequestProperty("Content-Type", "application/octet-stream");
		            connection.setRequestProperty("Prediction-Key", predictionKey);
		            connection.setDoOutput(true);

		            // Send the image data
		            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
		                wr.write(Base64.getDecoder().decode(base64Image));
		            }

		            // Read the response
		            int responseCode = connection.getResponseCode();
		            if (responseCode == HttpURLConnection.HTTP_OK) {
		                // Parse and handle the prediction results (in JSON format)
		                try (InputStream inputStream = connection.getInputStream()) {
		                    byte[] responseBytes = readAllBytes(inputStream);
		                    String jsonResponse = new String(responseBytes);

		                    System.out.println(jsonResponse);
		                    counterVO.setImgPillCount(readJsonThresold(base64Image));
		                }
		            } else {
		                System.err.println("Prediction request failed. Response code: " + responseCode);
		            }
				 	//
		            counterVO.setImgPath(filePath);
		            counterVO.setName(fileName);
		            
		            //for get count and set Count
		            
		            iaRepository.save(counterVO);
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	public List<PillsCounterVO> getUploadImg()
	{
		List<PillsCounterVO> listPill=new ArrayList<>();
		listPill=iaRepository.findAll();
		
		for(int i=0;i<listPill.size();i++)
		{
			loadAndSetImageContent(listPill.get(i));
		}
		return listPill;
	}
	
	private static byte[] readAllBytes(InputStream inputStream) throws IOException {
        // Replace with logic to read all bytes from the input stream
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
	
	private static String encodeImageToBase64(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }
	
	
	public byte[] loadAndSetImageContent(PillsCounterVO counterVO) {
        // Load image content from file path and set it in the entity
        try {
            Path imagePath = Paths.get(counterVO.getImgPath()); // Adjust the path accordingly
            counterVO.setByteImage(Files.readAllBytes(imagePath));
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return counterVO.getByteImage();
    }
	
	private String readJsonThresold(String jsonData) throws JsonProcessingException, IOException
	{
		ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(JsonParser.Feature.ALLOW_COMMENTS);

        JsonNode rootNode = objectMapper.readTree(jsonData);
        JsonNode predictionsNode = rootNode.get("predictions");
        int countAboveThreshold = 0;

        Iterator<JsonNode> predictionsIterator = predictionsNode.elements();
        while (predictionsIterator.hasNext()) {
            JsonNode prediction = predictionsIterator.next();
            if (prediction.get("probability").asDouble() > Double.valueOf(threshold)) {
                countAboveThreshold++;
            }
        }

        // Calculate percentage
        return String.valueOf(countAboveThreshold);

	}
}
