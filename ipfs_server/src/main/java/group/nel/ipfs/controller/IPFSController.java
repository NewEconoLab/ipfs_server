package group.nel.ipfs.controller;

import group.nel.ipfs.lib.IPFSClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IPFSController {

	@GetMapping("/test")
	public String test() {
		return "ipfs test message!  [version]: v0.0.0.1, [content]:"+Math.random();
	}
	
	@PostMapping("/addcontent")
	public IPFSRes addContent(@RequestBody AddContentReq req) {
		String res = new IPFSClient(null).addContent(req.content);
		return new IPFSRes(res);
	}
	
	@PostMapping("/getcontent")
	public IPFSRes getContent(@RequestBody GetContentReq req) {
		String res = new IPFSClient(null).getContent(req.hash);
		return new IPFSRes(res);
	}
	
	@GetMapping("/keys")
	public String list() {
		return new IPFSClient(null).keys();
	}
}
class AddContentReq {
	public String content;
}
class GetContentReq {
	public String hash;
}
class IPFSRes {
	public boolean isSuccess;
	public String result;
	
	public IPFSRes(String result) {
		isSuccess = result.endsWith("err:") ? false:true;
		this.result = result;
	}
}
