package site.Ulbame.BE.board.controller;

import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import site.Ulbame.BE.board.dto.BoardDto;
import site.Ulbame.BE.board.service.BoardService;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(path="/post")
	public List<BoardDto> getBoardList(
			@RequestParam int firstPostIndex,
			@RequestParam int lastPostIndex,
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String contents,
			@RequestParam(required = false) String creatorId)
			throws Exception{
		System.out.println(title + contents + creatorId + "파라메터");
		return boardService.getBoardList(firstPostIndex, lastPostIndex, title, contents, creatorId);
	}
//	public ResponseEntity<List<BoardDto>> getBoardList(@RequestParam int firstPostIndex, @RequestParam int lastPostIndex)
//			throws Exception{
//		return ResponseEntity.status(HttpStatus.OK).body(boardService.getBoardList(firstPostIndex, lastPostIndex));
//	}

	@GetMapping(path="/post/listSize")
	public Map<String, Long> getBoardSize(
			@RequestParam(required = false) String title,
			@RequestParam(required = false) String contents,
			@RequestParam(required = false) String creatorId
			) throws Exception{
		return boardService.getBoardSize(title, contents, creatorId);
	}
	
	@PostMapping(path="/post")
	public void createPost(@RequestBody BoardDto board) throws Exception{
		boardService.createPost(board, null);
	}
	
	@GetMapping(path="/post/{boardIdx}")
	public BoardDto getPost(@PathVariable("boardIdx") int boardIdx) throws Exception{
		return boardService.getPost(boardIdx);
	}
	
	@PatchMapping(path="/post")
	public void modifyPost(@RequestBody BoardDto board) throws Exception{
		boardService.modifyPost(board);
	}
	
	@DeleteMapping(path="/post/{boardIdx}")
	public void deletePost(@PathVariable("boardIdx") int boardIdx) throws Exception{
		boardService.deletePost(boardIdx);
	}
}
