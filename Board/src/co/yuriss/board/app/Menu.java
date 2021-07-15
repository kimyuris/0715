package co.yuriss.board.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.yuriss.board.service.BoardService;
import co.yuriss.board.serviceimpl.BoardServiceImpl;
import co.yuriss.board.vo.BoardVO;

public class Menu {
	private Scanner scn = new Scanner(System.in);
	private BoardService dao = new BoardServiceImpl();
	

	private void menuTitle() {
		System.out.println("===========================");
		System.out.println("========= 게 시 판 =========");
		System.out.println("-------- 1. 글 조회 --------");
		System.out.println("-------- 2. 글 쓰기 --------");
		System.out.println("-------- 3. 글 삭제 --------");
		System.out.println("-------- 4. 종   료 --------");
		System.out.println("===========================");
	}

	private void mainManu() {

		int nomber;
		boolean b = false;
		do {
			menuTitle();
			BroardSelectList();
			switch (nomber = scn.nextInt()) {
			case 1:
				BoardSelect();
				break;
			case 2:
				BoardInsert();
				break;
			case 3:
				BoardDelete();
				break;
			case 4:
				b = true;
				System.out.println("===== Good Bye !!!! =====");
				break;
			}
		} while (!b);

	}

	private void BroardSelectList() {
		// TODO 전체리스트 boardid,writer,title,enterdate,hit
		List<BoardVO> boards = new ArrayList<BoardVO>();
		boards = dao.boardSelectList();
		for (BoardVO board : boards) {
			board.toString();
		}

	}

	private void BoardDelete() {
		// TODO 글 삭제
		BoardVO vo = new BoardVO();
		System.out.println("삭제할 ID를 입력하세요 >>> ");
		vo.setBoardId(scn.next());
		scn.nextLine();

		int n = dao.boardDelete(vo);
		if (n != 0) {
			System.out.println("삭제가 정상적으로 되었습니다.");
		} else {
			System.out.println("삭제가 실패했습니다.");
		}

	}

	private void BoardInsert() {
		// TODO 글쓰기 insert into board (boardid,writer,title,subject) values
		BoardVO vo = new BoardVO();
		System.out.println("회원 ID를 입력하세요 >>> ");
		vo.setBoardId(scn.next());
		scn.nextLine();
		System.out.println("작성자를 입력하세요 >>> ");
		vo.setWriter(scn.next());
		scn.nextLine();
		System.out.println("제목을 입력하세요 >>> ");
		vo.setTitle(scn.next());
		scn.nextLine();
		System.out.println("내용을 입력하세요 >>>");
		vo.setSubject(scn.next());
		scn.nextLine();

		int n = dao.boardInsert(vo);
		if (n != 0) {
			System.out.println("입력이 정상적으로 되었습니다.");
		} else {
			System.out.println("입력에 실패했습니다.");
		}

	}

	private void BoardSelect() {
		// TODO 글조회
		BoardVO vo = new BoardVO();
		System.out.println("조회할 회원 ID를 입력하세요 >>> ");
		vo.setBoardId(scn.next());
		scn.nextLine();

		vo = dao.boardSelect(vo);
		vo.toString();

	}

	public void run() {
		mainManu();
		scn.close();
	}

}
