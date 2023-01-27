package common;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageVO {

	//검색관련 필드
	private String search="all", keyword="", search_dept="-1", search_rank="-1", search_code="-1";
	private String viewType ="list";
	
	//페이지당 보여질 목록의 수
	private int pageList = 10;
	//블럭당 보여질 페이지의수 
	private int blockPage= 10;

	//총 목록 수 db에서 조회해와서 총페이지 수와 총 블록 수를 계산..
	private int totalList;
	//총 페이지 수
	private int totalPage;
	// 총 블록수
	private int totalBlock;
	
	
	//현재 페이지 번호// 초기페이지가 없으면 오류가 생기므로 1로 초기화를 시켜주었다!
	private int curPage=1;
	//각 페이지의 시작 목록 번호 현재 페이지 번호에 따라 계산
	//각 페이지 끝 목록번호
	private int beginList, endList;
	
	//현재 블록 번호 시작페이지 번호와 끝번호 페이지 계산
	private int curBlock, endPage, beginPage;

	//totalList 이외에는 모두 계산이기 때문에 메소드를 생성해보자
	public void setTotalList(int totalList) {
		this.totalList = totalList;
		// 총 목록수 들어옴.
		//총 페이지 수는 목록수 / 한페이지당 수 
		totalPage = totalList / pageList;
		// 88페이지 = 877 / 10 = 87.. 7
		
		if(totalList % pageList > 0 ) ++totalPage;
		//나머지가 있어서 총페이지 수를 하나 늘렸다.
		
		//총 블록수 8블록 88/10 = 8 ... 8
		totalBlock = totalPage/ blockPage;
		if(totalPage % blockPage>0) ++totalBlock;
		// 나머지가 있어서 블록수가 하나 늘었음.
		
		
		//각페이지의 끝 목록번호 : 총 목록수 - (페이지번호 - 1 ) * 페이지당 보여질 목록수
		endList = totalList - (curPage-1) * pageList;
		beginList = endList - (pageList-1);
		
		
		// 현재 블록 번호 : 페이지 번호 / 블록당 보여질 페이지 수
		// 각 블럭의 끝 페이지 번호 : 블록번호 * 블록당 보여질 페이지 수
		// 각 블럭의 시작 페이지 번호 : 끝 페이지 번호 - (블럭당 보여질 페이지수 - 1)
		curBlock = curPage/blockPage;
		if( curPage % blockPage >0 ) ++curBlock;
			endPage = curBlock * blockPage;
			beginPage = endPage - ( blockPage -1 );
			
		//끝 페이지 번호가 총 페이지 수보다 클 수 없으므로 총 페이지 수를 끝 페이지 번호로 한다.
			if( totalPage < endPage ) endPage = totalPage;
			
			//공지글 10건을 담아둘 곳이 필요. notice에 새로운 클래스 NoticePageVO 생성 (PageVO를 상속받은)
	}
}
