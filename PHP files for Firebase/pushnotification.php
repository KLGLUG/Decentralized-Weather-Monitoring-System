<?php 
	function send_notification($tokens, $message)
	{
		$url = 'https://fcm.googleapis.com/fcm/send';
		$fields = array(
			 'registration_ids' => $tokens,
			 'data' => $message
			);
		$headers = array(
			'Authorization:key = AAAAHXsVMpQ:APA91bGkRb0EO225BrvA52iXdDCU3h_zbANuhk8vpH0zOcMxSgI4QRJKuPSRdukA0YC5TLqcwFQnfH14i6fsqIRxSTp0_jdoXXfgNQAfrgY92KZm8sat-RUbwNDHtcpFKQjybYuLbgTA',
			'Content-Type: application/json'
			);
	   $ch = curl_init(); 
       curl_setopt($ch, CURLOPT_URL, $url);
       curl_setopt($ch, CURLOPT_POST, true);
       curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);  
       curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
       curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
       $result = curl_exec($ch);        
       if ($result === FALSE) {
           die('Curl failed: ' . curl_error($ch));
       }
       curl_close($ch);
       return $result;
	}
	echo "hello";
	$conn = mysqli_connect("localhost","root","root","FCM");
	$sql = " Select Token From users";
	$result = mysqli_query($conn,$sql);
	$tokens = array();
	if(mysqli_num_rows($result) > 0 ){
		while ($row = mysqli_fetch_assoc($result)) {
			$tokens[] = $row["Token"];
			
		}
	}
	
	$sql1= "select temperature, humidity, smoke from monitor ORDER BY timestamp DESC LIMIT 10";
	$loop= mysqli_query($conn,$sql1);
	$flagTEMP=FALSE;
	$flagHUM=FALSE;
	$flagSMOKE=FALSE;
	//if(mysqli_num_rows($loop) > 0 ){
		while($row = mysqli_fetch_array($loop)){
		if($row['temperature']>35){$flagTEMP=TRUE;}
		if($row['humidity']>65){$flagHUM=TRUE;}
		if($row['smoke']>370){$flagSMOKE=TRUE;}
		}
	//}
	mysqli_close($conn);
	$messagetemp = array("message" => " TEMPERATURE HIGH");
	if($flagTEMP===TRUE){
	$message_status = send_notification($tokens, $messagetemp); 
	
	echo $message_status;
	}


	$messagehum = array("message" => " HUMIDITY HIGH");
	if($flagHUM===TRUE){
	$message_status = send_notification($tokens, $messagehum); 
	
	echo $message_status;
	}


	$messagesmk = array("message" => " SMOKE HIGH");
	if($flagSMOKE===TRUE){
	$message_status = send_notification($tokens, $messagesmk); 
	
	echo $message_status;
	}
 ?>
