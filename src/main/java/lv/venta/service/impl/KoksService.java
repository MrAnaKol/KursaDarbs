package lv.venta.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.repo.IKoksRepo;
import lv.venta.service.IKoksService;

@Service
public class KoksService implements IKoksService{
	
	@Autowired
	private IKoksRepo kokaRepo;
}
