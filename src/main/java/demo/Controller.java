package demo;

import java.net.URI;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.justinsb.etcd.EtcdClient;
import com.justinsb.etcd.EtcdClientException;
import com.justinsb.etcd.EtcdResult;


@RestController
public class Controller {
	
	@RequestMapping(value="/api/v1/counter",method=RequestMethod.GET)
	public int getCount() throws EtcdClientException {
		//EtcdClient client = new EtcdClient(URI.create("http://54.67.103.220:4001/"));
		EtcdClient client = new EtcdClient(URI.create("http://54.183.58.249:4001/"));
		//System.out.println("Reached");
		EtcdResult result ;
		String key = "/010003500";
		
		result = client.get(key);
		//System.out.println("Reached 2");
		int counter = Integer.parseInt(result.node.value);
		System.out.println("Current value counter for "+key+" is: "+counter);
		counter++;
		
		result = client.set(key, String.valueOf(counter));
		System.out.println("New value counter for "+key+" is: "+counter);
		
		EtcdClient.close(null);
		return counter;
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String systemUp(){
		return "System is up!!";
	}

}
