package in.LearniflyEducation.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.LearniflyEducation.main.entity.FollowUps;
import in.LearniflyEducation.main.repository.FollowUpsRepo;

@Service
public class FollowUpsService {

	@Autowired
	private FollowUpsRepo followUpsRepo;
	
	public void addFollowUps(FollowUps followUps) {
		Optional<FollowUps> optional = followUpsRepo.findByPhoneNo(followUps.getPhoneNo());
		if(optional.isPresent()) {
			FollowUps oldFollowUps = optional.get();
			oldFollowUps.setFollowUps(followUps.getFollowUps());
			System.out.println(oldFollowUps);
			followUpsRepo.save(oldFollowUps);
		}else {
			followUpsRepo.save(followUps);
		}
	}
	
	public List<FollowUps> getMyFollowUps(String empEmail,String followUps){
		return followUpsRepo.findByEmpEmailAndFollowUps(empEmail, followUps);
	}
	
	public void deleteByPhoneNo(String phoneNo) {
		Optional<FollowUps> optional =followUpsRepo.findByPhoneNo(phoneNo);
		if(optional.isPresent()) {
			FollowUps followUps1 =optional.get();
			followUpsRepo.delete(followUps1);
		}
	}
}
