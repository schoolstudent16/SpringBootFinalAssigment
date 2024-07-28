package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.entity.Music;
import com.example.demo.form.MusicForm;
import com.example.demo.service.MusicService;



@Controller
@SessionAttributes({"MusicForm","SongId"})
public class MusicController {
	
	@Autowired
	MusicService service;
	
//	Modelにリクエストデータをセットする
	@ModelAttribute("MusicForm")
	public MusicForm inputData() {
		return new MusicForm();
	}
	
	
//	トップページへのルーティング
	@GetMapping("index")
	public String getMethodName() {
		return "index";
	}
	
	
//	一覧表示画面へのルーティング
	@PostMapping(value = "action", params = "list")
	public String showListView(Model model) {
		//TODO: process POST request
		fetchDataBase(model);
		return "list";
	}
	
	
//	全データ取得処理
	void fetchDataBase(Model model){
//		一覧表示用のデータをModelにセット
		model.addAttribute("list",service.findAll());
	}
	
	
//	更新・削除データ取得処理
	void selectData(Model model, Integer SongId){
//		データベースから取得したデータをモデルに格納(SongIdを使い、データベースからデータを取得)
		model.addAttribute("selectData", service.findById(SongId));
//		SongIdをセッションに格納
		model.addAttribute("SongId", SongId);
	}
	
	
//	インサート処理関連
	
	//	登録入力画面へのルーティング
		@PostMapping(value = "action", params = "register")
		public String showRegisterView() {
			//TODO: process POST request
			return "register";
		}
		
	//	登録完了画面へのルーティング
		@PostMapping("insert")
		public String showInsertView(@ModelAttribute("MusicForm") MusicForm f) {
			//TODO: process POST request
//			インサートするデータをセット
			Music music = new Music(null, f.getSongName(), f.getSinger());
//			インサート開始
			service.InsertMusic(music);
			
			return "insert-complete";
		}
		
	
		
//	更新処理関連
		
//		更新データ選択画面へのルーティング
		@PostMapping(value = "action", params = "updateSelectData")
		public String showUpdateView(Model model) {
			//TODO: process POST request
//			取得したデータをlistにセット
			fetchDataBase(model);

			return "update";
		}

//		更新データ入力画面へのルーティング
		@PostMapping("updateDataInput")
		public String showUpdateSelectView(@RequestParam("SongId") Integer SongId, Model model) {
			//TODO: process POST request
			
			selectData(model, SongId);
			
			return "updateDataInput";
		}
		
//		更新処理実行
		@PostMapping("UpdateProcess")
		public String UpdateProcess(@ModelAttribute("MusicForm") MusicForm f, @ModelAttribute("SongId") Integer id, SessionStatus sessionStatus) {
			//TODO: process POST request
//			更新するデータをセット
			Music music = new Music(id, f.getSongName(), f.getSinger());
//			更新開始
			service.UpdateData(music);
			// セッションをクリア
	        sessionStatus.setComplete();
			return "index";
		}
		
		
//	削除処理関連
	
	//	削除画面へのルーティング
		@PostMapping(value = "action", params = "delete")
		public String showDeleteView(Model model) {
			//TODO: process POST request
			fetchDataBase(model);
			return "delete";
		}
		
//		削除処理実行
		@PostMapping("deleteProcess")
		public String showDeleteSelectView(@RequestParam("SongId") Integer SongId, Model model) {
			//TODO: process POST request
//			削除開始
			service.DeleteById(SongId);
			
			return "delete-complete";
		}

}
