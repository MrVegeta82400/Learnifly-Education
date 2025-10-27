package in.LearniflyEducation.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.LearniflyEducation.main.entity.Inqueiry;
import in.LearniflyEducation.main.repository.InquiryRepository;

@Service
public class InquiryService {
	
	@Autowired
	private InquiryRepository inquiryRepository;
	
	public void addNewInquiry(Inqueiry inqueiry) {
		inquiryRepository.save(inqueiry);	
	}
	
	public List<Inqueiry> findInquiryUsingPhoneNo(String phoneNo){
		return inquiryRepository.findByPhoneno(phoneNo);
	}
}
