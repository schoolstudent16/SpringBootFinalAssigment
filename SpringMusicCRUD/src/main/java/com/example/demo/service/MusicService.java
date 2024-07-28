package com.example.demo.service;

import java.util.Optional;

import com.example.demo.entity.Music;

public interface MusicService {
	Iterable<Music> findAll();
	public void InsertMusic(Music m);
	Optional<Music> findById(Integer id);
	public void UpdateData(Music m);
	public void DeleteById(Integer id);
}
