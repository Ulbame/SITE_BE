package site.Ulbame.BE.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import site.Ulbame.BE.board.dto.BoardDto;
import site.Ulbame.BE.board.dto.BoardFileDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardList(
			@Param("firstPostIndex") int firstPostIndex,
			@Param("lastPostIndex") int lastPostIndex,
			@Param ("title") String title,
			@Param ("contents") String contents,
			@Param ("creatorId") String creatorId
		) throws Exception;
	
	void insertBoard(BoardDto board) throws Exception;

	BoardDto selectBoardDetail(int boardIdx) throws Exception;

	void updateHitCount(int boardIdx) throws Exception;
	
	void updateBoard(BoardDto board) throws Exception;
	
	void deleteBoard(int boardIdx) throws Exception;

	void insertBoardFileList(List<BoardFileDto> list) throws Exception;

	List<BoardFileDto> selectBoardFileList(int boardIdx) throws Exception;

	Map<String, Long> selectBoardSize(
			@Param ("title") String title,
			@Param ("contents") String contents,
			@Param ("creatorId") String creatorId
	) throws Exception;

	BoardFileDto selectBoardFileInformation(@Param("idx") int idx, @Param("boardIdx" )int boardIdx);
}
