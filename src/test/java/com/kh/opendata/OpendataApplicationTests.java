package com.kh.opendata;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


@SpringBootTest
class OpendataApplicationTests {

	@Test
	void contextLoads() throws IOException {
//		String appKey = "PC2UitJYo8%2FiesWxLC%2F2FhMAmvBy3DxWJ4qUkIX8mybVAoyRgC0AjA%2FukXS4onGkg759rqltQ1n2RL5JB092Aw%3D%3D";
//		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
//		url += "?serviceKey="+appKey;
//		url += "&returnType=json";
//
//		url += "&sidoName=" + URLEncoder.encode("서울","UTF-8");
//		System.out.println(url);
//
//		URL requestUrl = new URL(url);
//		// 2)java.net.HttpURLConnection 객체 생성 (URL 객체)
//		HttpURLConnection urlconn = (HttpURLConnection) requestUrl.openConnection();
//		urlconn.setRequestMethod("GET");
//		//API 서버로 요청 보낸 후 입력 스트림을 통해 응답 데이터 읽기
//		//	*BufferdReader : 보조스트림/한줄 씩 읽어옴
//		//	*InputStream (1byte단위)	/ Reader (2byte단위)
//		//	*InputStreamReader : "바이트 스트림" ---> "문자 스트림" 바꿔주는 스트림
//		BufferedReader buf = new BufferedReader(new InputStreamReader(urlconn.getInputStream()));
//		String line;
//		String resopnseText = "";
//		while ((line = buf.readLine()) != null){
//			resopnseText += line;
//		}
//		System.out.println(resopnseText);
//		//응답데이터 ---> VO 객체 파싱 작업 (JsonObject, JsonArray, JsonElement 객체 이용 파싱) -> Gson 라이브러리 사용
//		// 스트림 자원 반납
//		buf.close();
//		urlconn.disconnect();
//
//		JsonObject totalObj = JsonParser.parseString(resopnseText).getAsJsonObject();
//		JsonObject responseObj = totalObj.getAsJsonObject("response");
//		// => 응답 데이터 중 "response" 키값에 해당되는 데이터 추출
//		JsonObject bodyObj = responseObj.getAsJsonObject("body");
//		JsonArray items = bodyObj.getAsJsonArray("items");
//		// => "items"라는 키값에 해당되는 데이터 추출
//		int totalCount = bodyObj.get("totalCount").getAsInt();
//		// => totalCount 키값에 해당되는 데이터 추출
//		System.out.println(totalCount);
//		System.out.println(items);
//
//		ArrayList<AirVo> list = new ArrayList<>();
//		for(int i=0; i<items.size(); i++){
//			JsonObject item = items.get(i).getAsJsonObject();
//			//System.out.println(item);
//			AirVo air = new AirVo();
//			air.setStationName(item.get("stationName").getAsString());
//			air.setDataTime(item.get("dataTime").getAsString());
//			air.setKhaiValue(item.get("khaiValue").getAsString());
//			air.setPm10Value(item.get("pm10Value").getAsString());
//			air.setSo2Value(item.get("so2Value").getAsString());
//			air.setCoValue(item.get("coValue").getAsString());
//			air.setPm10Value(item.get("pm10Value").getAsString());
//			air.setNo2Value(item.get("no2Value").getAsString());
//			list.add(air);
//		}
//		for(AirVo air : list){
//			System.out.println(air);
//		}

		String urlStr = "http://apis.data.go.kr/1741000/TsunamiShelter4/getTsunamiShelter4List"
				+ "?serviceKey=" + "PC2UitJYo8%2FiesWxLC%2F2FhMAmvBy3DxWJ4qUkIX8mybVAoyRgC0AjA%2FukXS4onGkg759rqltQ1n2RL5JB092Aw%3D%3D"
				+ "&pageNo=1"
				+ "&numOfRows=10"
				+ "&type=json";

		URL url = new URL(urlStr);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		System.out.println("Response code: " + conn.getResponseCode());

		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println(sb.toString());

	}

}
