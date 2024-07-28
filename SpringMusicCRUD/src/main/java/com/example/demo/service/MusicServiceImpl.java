package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Music;
import com.example.demo.repository.MusicCrudRepository;

@Service
public class MusicServiceImpl implements MusicService {

	@Autowired
	MusicCrudRepository repository;
	
	@Override
	public Iterable<Music> findAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}

	@Override
	public void InsertMusic(Music m) {
		// TODO 自動生成されたメソッド・スタブ
		repository.save(m);
	}

	@Override
	public Optional<Music> findById(Integer id) {
		
		return repository.findById(id);
		
	}

	@Override
	public void UpdateData(Music m) {
		// TODO 自動生成されたメソッド・スタブ
		repository.save(m);
	}

	@Override
	public void DeleteById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteById(id);
	}

}
