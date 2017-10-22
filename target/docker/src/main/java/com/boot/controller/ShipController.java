package com.boot.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

@RestController
@RequestMapping("api/v1")
public class ShipController {
	
	@Resource
	private ShipwreckRepository shipwreckRepository;
	
	@RequestMapping(value = "shipwrecks", method = RequestMethod.GET)
	public List<Shipwreck> getList() {
		return shipwreckRepository.findAll();
	}

	@RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
	public Shipwreck create(@RequestBody Shipwreck shipwreck) {
		return shipwreckRepository.saveAndFlush(shipwreck);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
	public Shipwreck get(@PathVariable long id) {
		return shipwreckRepository.findOne(id);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
	public Shipwreck update(@PathVariable long id, @RequestBody Shipwreck shipwreck) {
		Shipwreck oldShipwreck = shipwreckRepository.findOne(id);
		BeanUtils.copyProperties(shipwreck, oldShipwreck);
		return shipwreckRepository.saveAndFlush(oldShipwreck);
	}

	// can also return void/object
	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
	public Shipwreck delete(@PathVariable long id) {
		Shipwreck oldShipwreck = shipwreckRepository.findOne(id);
		shipwreckRepository.delete(oldShipwreck);
		return oldShipwreck;
	}
}
