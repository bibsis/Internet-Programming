package org.elsys.netprog.rest;
 
import java.net.URI;
import org.json.*;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
 
 
@Path("/guess")
public class GameController 
{	
    static private String hash;
    static private int lenght = 1;
    static byte[] b = new byte[lenght];
    static int counter = 1;
 
    @GET
    @Path("/get")
    @Produces(value={MediaType.APPLICATION_JSON})
    public Response getHash() throws NoSuchAlgorithmException{
    	 if(counter==1){
    		 new Random().nextBytes(b);
    	        MessageDigest md = MessageDigest.getInstance("MD5");
    	        md.update(b);
    	        hash = DatatypeConverter.printHexBinary(md.digest()).toUpperCase();
    		  counter--;
    	 }
    	 JSONObject obj = new JSONObject();
         obj.put("hash", this.hash);
         obj.put("lenght", this.lenght);
         return Response.status(200).entity(obj.toString()).build();
    }
   
    @POST
    @Path("/post/{OG}")
    @Produces(value={MediaType.APPLICATION_JSON})
    public  Response guess( @PathParam("OG") String guess , JSONObject input) throws Exception{
        if (guess.endsWith(hash) && input.get("byte").equals(b))
        {
            return Response.status(200).build();
        }else
        {
            return Response.status(406).build();
        }
           
       
    }
 
    /* public String givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect() {
         
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 3;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
     
        return(generatedString);
    } */
}