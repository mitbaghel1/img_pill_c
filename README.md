python detect.py --source path/to/your/input/video.mp4 --weights path/to/your/trained/model.pt --save-txt --save-conf --save-crop --project path/to/save/directory --name output_video
python train.py --img 640 --batch 2 --epochs 60 --data data.yaml --cfg models/yolov5s.yaml --name pill_custom_mode
python detect.py --weights runs\train\pill_custom_mode\weights\last.pt --img 640 --conf 0.25 --source ../video.mp4


python detect.py --source C:\Users\mitba\Downloads\yolov5-master\Train_data\train\images\tylenol_cold-94-_jpg.rf.3cecf30d1942ce83cc8e2520f6c633db.jpg --weights runs\train\pill_custom_mode\weights\last.pt --save-txt --save-conf --save-crop --project runs\detect\ --name testImg.jpg
