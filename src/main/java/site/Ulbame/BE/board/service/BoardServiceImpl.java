package site.Ulbame.BE.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import site.Ulbame.BE.board.common.FileUtils;
import site.Ulbame.BE.board.dto.BoardDto;
import site.Ulbame.BE.board.dto.BoardFileDto;
import site.Ulbame.BE.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<BoardDto> getBoardList(int firstPostIndex, int lastPostIndex, String title, String contents, String creatorId) throws Exception {
		return boardMapper.selectBoardList(firstPostIndex, lastPostIndex, title, contents, creatorId);
	}



	@Override
	public Map<String, Long> getBoardSize(String title, String contents, String creatorId) throws Exception {
		return boardMapper.selectBoardSize(title, contents, creatorId);
	}
	
	@Override
	public void createPost(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardMapper.insertBoard(board);
		List<BoardFileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false){
			boardMapper.insertBoardFileList(list);
		}
	}

	@Override
	public BoardDto getPost(int boardIdx) throws Exception{
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
		board.setFileList(fileList);
		
		boardMapper.updateHitCount(boardIdx);
		
		return board;
	}
	
	@Override
	public void modifyPost(BoardDto board) throws Exception {
		System.out.println(board);
		boardMapper.updateBoard(board);
	}

	@Override
	public void deletePost(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}
	
	@Override
	public BoardFileDto selectBoardFileInformation(int idx, int boardIdx) throws Exception {
		return boardMapper.selectBoardFileInformation(idx, boardIdx);
	}
}	

