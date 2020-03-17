package com.project.service;

import java.io.IOException;

import com.project.domain.ResumeFile;


public interface ResourceDao {

	ResumeFile addPhoto(ResumeFile file) throws IOException;

}
