package in.LearniflyEducation.main.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.LearniflyEducation.main.entity.FollowUps;
import in.LearniflyEducation.main.service.FollowUpsService;

@RestController
@RequestMapping("/api")
public class FollowUpsApi {

	@Autowired
	private FollowUpsService followUpsService;
	@GetMapping("/myFollowUps")
	public ResponseEntity<List<FollowUps>> getFollowUpCustomer(
			@RequestParam("empEmail") String empEmail,
			@RequestParam("followUps")String followUps){
		List<FollowUps> followUpsList= followUpsService.getMyFollowUps(empEmail, followUps);
		return ResponseEntity.ok(followUpsList);
	}
}
