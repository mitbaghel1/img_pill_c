package com.ia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "dtl_pills")
public class PillsCounterVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "img_name")
	private String name;

	@Column(name = "img_pill_count")
	private String imgPillCount;

	@Column(name = "img_path")
	private String imgPath;

	@Transient
	private MultipartFile file;
	
	@Transient
	private byte[] byteImage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgPillCount() {
		return imgPillCount;
	}

	public void setImgPillCount(String imgPillCount) {
		this.imgPillCount = imgPillCount;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public byte[] getByteImage() {
		return byteImage;
	}

	public void setByteImage(byte[] byteImage) {
		this.byteImage = byteImage;
	}
}
