package co.yuriss.board.service;

import java.util.List;

import co.yuriss.board.vo.BoardVO;

public interface BoardService {
	List<BoardVO> boardSelectList();

	BoardVO boardSelect(BoardVO vo);

	int boardInsert(BoardVO vo);

	int boardDelete(BoardVO vo);
}
