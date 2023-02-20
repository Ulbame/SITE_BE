package site.Ulbame.BE.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import site.Ulbame.BE.board.dto.BoardDto;
import site.Ulbame.BE.board.dto.BoardFileDto;

public interface BoardService {
	
	List<BoardDto> getBoardList(int firstPostIndex, int lastPostIndex, String title, String contents, String creatorId) throws Exception;

	Map<String, Long> getBoardSize(String title, String contents, String creatorId) throws Exception;
	
	void createPost(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

	BoardDto getPost(int boardIdx) throws Exception;

	void modifyPost(BoardDto board) throws Exception;

	void deletePost(int boardIdx) throws Exception;

	BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception; 
}
