package co.yuriss.board.serviceimpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yuriss.board.dao.DAO;
import co.yuriss.board.service.BoardService;
import co.yuriss.board.vo.BoardVO;

public class BoardServiceImpl extends DAO implements BoardService {
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<BoardVO> boardSelectList() {
		// TODO 전체리스트 보여주기
		List<BoardVO> boards = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "select * from board";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardId(rs.getString("boardid"));
				vo.setWriter(rs.getNString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setSubject(rs.getString("subject"));
				vo.setHit(rs.getInt("hit"));

				boards.add(vo); // 리스트에 추가하는 것
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return boards;

	}

	@Override
	public BoardVO boardSelect(BoardVO vo) {
		// TODO 글조회
		String sql = "select subject from board where boardid=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardId());
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new BoardVO();
				vo.setSubject(rs.getString("subject"));
				hitUpdate(vo.getBoardId());

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return vo;

	}

	private int hitUpdate(String boardId) {
		String sql = "update board set hit =hit+1 where boardid=?";
		int n = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, boardId);
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return n;

	}

	@Override
	public int boardInsert(BoardVO vo) {
		// TODO 글쓰기
		int n = 0;
		String sql = "insert into board (boardid,writer,title,subject) values(?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardId());
			psmt.setString(2, vo.getWriter());
			psmt.setString(3, vo.getTitle());
			psmt.setString(4, vo.getSubject());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return n;
	}

	@Override
	public int boardDelete(BoardVO vo) {
		// TODO 글삭제
		int n = 0;
		String sql = "delete from board where boardid =?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getBoardId());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
}
