import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MoiveTime {
private WebDriver driver; //기본 드라이버
	
	// 웹드라이버에 필요한 기본 소스
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; //각 브라우저에 따라 다름
	public static final String WEB_DRIVER_PATH = "C:/Users/WAS/Desktop/KOSMO/JavaLib/chromedriver_win32\\\\chromedriver.exe"; //각 브라저에 맞는 드라이버 다운로드 후 경로 설정
	
	private String base_url; //연결할 웹페이지 링크
	WebDriverWait wait; // 페이지 로딩시 까지 대기해주는 클래스
	
	List<WebElement> elements = null; // 각 결과를 넣어줄 List
	Map<String,List> crawlingData=new HashMap<String, List>(); // 각 페이지로 값을 넘겨줄 Map
	
	
	public MoiveTime() {
		//기본셋팅
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions(); 
		//options.setHeadless(true); // 활성화 시 웹페이지 안나옴 -> 속도 개선
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver,10);
		base_url="http://www.lottecinema.co.kr/LCHS/Contents/ticketing/ticketing.aspx"; // 접속할 웹페이지
		
		//base_url = "https://www.justwatch.com/kr/%EC%98%81%ED%99%94/alradin-2019";
		//https://www.justwatch.com/kr/%EC%98%81%ED%99%94
		//https://www.justwatch.com/kr
	}
	
	public static void main(String[] args) {
		MoiveTime selTest = new MoiveTime();
		selTest.crawl();
	}////////////////////////////////////////////////////////
	
	
	//생성자
	//{"rental",리스트}
	//{"buy",리스트}
	public Map<String, List> crawl() {
		try {
			
			driver.get(base_url);
			
			//방법1]페이지 값을 읽어올 수 있게 대기
			//Thread.sleep(2000);
			
			//html로 저장
			//String html = driver.getPageSource();
			
			//요소 찾아가기
			//driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/ion-tab/ion-content/div[4]/div/div/div/div[2]/div[2]/a/div/img")).click();
			//driver.findElement(By.xpath("/html/body/div[3]/div[1]/div/div[2]/ion-tab[2]/ion-content/div/div[2]/div/div[1]/div[3]/div/div/div/div/div[3]/div[2]/div[2]/div/a/div")).click();
			
			//홈페이지 내 자바스크립트 요소 가져올때 까지 대기
			//영화 상세보기 들어가는 소스
			
			//visibilityOfElementLocated = 해당 엘리먼트가 보여질떄까지 대기한다. 
			//즉 사용자가 직접 화면에 띄워서 봐야만 코드가 진행된다. 
			//해당 엘리먼트를가 있는 부분을 화면에 안 띄울 시 코드는 더이상 진행되지않고 무한정 대기 
			WebElement wElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div[1]/div/div[4]/div/div[1]/div[2]/div[2]/div[1]/ul/li[1]/div/ul/li[1]/a")));
			
			//presenceOfElementLocated : 화면과 관계없이 셀리니움이 찾는다
			//WebElement wElement =  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[1]/div/div[2]/ion-tab/ion-content/div[4]/div/div/div/div[2]/div[2]/a/div/img")));
			wElement.click();
			//Thread.sleep(2000);
			System.out.println("클릭까지 성공");
			
			
			
			
			System.out.println("요소 가져오기 전 성공");
			//원하는 요소 가져올때까지 대기
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"content\"]/div[3]/div[3]/div[2]/dl[1]")));
			//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div[1]/div/div[2]/ion-tab[2]/ion-content/div/div[2]/div/div[1]/div[2]/div/div/div/div/div[4]/div[2]/div[1]/div/a/div")));
			System.out.println("요소 가져오기 성공");
			
			
			
			
			
			
			//영화시간표 저장
			elements= driver.findElements(By.xpath("//*[@id=\"content\"]/div[3]/div[3]/div[2]/dl"));
			System.out.println("영화시간표 저장 성공");
			System.out.println("영화시간표 갯수:"+elements.size());
			List rental = new Vector();
			System.out.println("영화시간표 For문 전");
			for(WebElement element:elements) {
				System.out.println("영화시간표 for문 실행");
				rental.add(element.getText());
				System.out.println(element.getText());
				System.out.println(element.toString());
				
				
			}
			crawlingData.put("rental", rental);
			/*
			//구매 가격 저장
			elements= driver.findElements(By.xpath("/html/body/div[3]/div[1]/div/div[2]/ion-tab[2]/ion-content/div/div[2]/div/div[1]/div[2]/div/div/div/div/div[3]/div[2]/div/div/a/div"));
			System.out.println("구매 갯수:"+elements.size());
			List buy = new Vector();
			for(WebElement element:elements) {
				
				buy.add(element.getText());
				System.out.println(element.getText());
			}
			crawlingData.put("buy", buy);
			
			//줄거리 확인차 출력
			/*
			elements=driver.findElements(By.xpath("/html/body/div[3]/div[1]/div/div[2]/ion-tab[2]/ion-content/div/div[2]/div/div[1]/div[7]/div[3]/p[2]/span"));
			List summary = new Vector();
			for(WebElement element:elements) {
				summary.add(element.getText());
				System.out.println("줄거리 : "+element.getText());
			}
			crawlingData.put("summary",summary);
			*/
			//결과 확인차 출력해 보기
			/*
			String[] vendor = {"POOK","NAVER","GOOGLE"};
			System.out.println("대여 가격");
			
			for(int i=0;i<rental.size();i++) {
				System.out.print(String.format("%s:%s ",vendor[i],rental.get(i)));				
			}
			System.out.println("\r\n구매 가격");
			
			for(int i=0;i<buy.size();i++) {
				System.out.print(String.format("%s:%s ",vendor[i],buy.get(i)));				
			}
			*/
			
			//String html = driver.getPageSource();
			
			/*
 */
			/*
			System.out.println(html);
			
			///html/body/div[3]/div[1]/div/div[2]/ion-tab[2]/ion-content/div/div[2]/div/div[1]/div[3]/div/div/div/div/div[3]/div[2]/div[1]/div/a/div

			System.out.println(html);
			System.out.println("doc 생성전");
			Document doc = Jsoup.parse(html);
			System.out.println("doc 생성 후");
			System.out.println(doc);
			
			//iframe = driver.findElement(By.cssSelector('iframe'));
			
			
			
			
			
			
			
			//price-comparison__grid__row__price
			// 가격 가져오기
			int element = doc.select("div.price-comparison__grid__row__price").size();
			System.out.println("element: "+element);
			Node price = null;
			System.out.println("for문 전");
			for (int i = 0; i < element; i++) {
				price = doc.select("div.price-comparison__grid__row__price").get(i).childNode(0);
						//.get(i).childNode(0);		
				System.out.println(price);
				
			}
			
			Elements summary = doc.select("text-wrap-pre-line");
			System.out.println(summary);
			
			
			
			*/
			
			
			// String price = element.toString();
			//System.out.println(element);
			/*
			 * for(Element el : element.select("#text")) { System.out.println(el.text()); }
			 */
			//System.out.println("+++++++++++");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			//driver.close();
		}
		
		return crawlingData;

	}
}
