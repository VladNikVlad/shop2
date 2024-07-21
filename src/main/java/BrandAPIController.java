import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vladyslavnicko.gmail.api.model.BrandAPI;
import com.vladyslavnicko.gmail.api.service.APIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandAPIController {
	
	private final APIService apiService;

	@PostMapping
	public ResponseEntity<BrandAPI> creatAddBreand(@RequestBody BrandAPI brandApi){
		return ResponseEntity.ok(apiService.saveBrand(brandApi));
	}
}
