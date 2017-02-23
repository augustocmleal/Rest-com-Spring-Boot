package com.anser.testebackend.message;

import java.net.ConnectException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anser.testebackend.dao.daoImpl.SaleDaoImpl;
import com.anser.testebackend.vo.SaleVo;

@Component
public class Receiver {
	private CountDownLatch latch = new CountDownLatch(1);
	
	@Autowired
	private SaleDaoImpl saleDaoImpl;
	
	private static Logger LOGGER = Logger.getLogger("ReceiverLogger");

    public void receiveMessage(Integer saleId) {
        latch.countDown();
        sendPost(saleId);
    }
    

    public CountDownLatch getLatch() {
        return latch;
    }
    
    private void sendPost(Integer id){
    	try {
    		SaleVo sale = new SaleVo();
    		sale = saleDaoImpl.carregar(id);
    		String url = "http://web-tests-01.ansertecnologia.net:8081/sale";
    		HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            String json = "{\"id\":"+id+"}";
            HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
            post.setEntity(entity);
            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode() == 200){
            	String result = EntityUtils.toString(response.getEntity());
            	sale.setExternalId(result);
                saleDaoImpl.alterar(sale);
            } else {
            	LOGGER.info("Erro: "+ response.getStatusLine());
            }
		}catch (ConnectException ce) {
			LOGGER.info(ce.getMessage());
		    ce.printStackTrace();
		}  catch (Exception e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
		}
		    
		
    }
}
