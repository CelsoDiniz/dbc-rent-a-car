package br.com.targettrust.traccadastros.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.targettrust.traccadastros.entidades.Reserva;
import br.com.targettrust.traccadastros.repositorio.ReservaRepository;

@RestController
@RequestMapping("reservas")
public class ReservaController {

	// TODO 1 Implementar métodos para criação, alteração e cancelamento de reserva
	@Autowired
	private ReservaRepository reservaRepository;
	

	public HttpEntity<Reserva> createReserva(@Valid @RequestBody Reserva reserva){
		if(reserva == null || reserva.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(reservaRepository.save(reserva));	
	}
	
	public HttpEntity<Reserva> updateReserva(@PathVariable("id") Long id, 
			@Valid @RequestBody Reserva reserva) {
		Optional<Reserva> dbReserva = reservaRepository.findById(id);
		if(dbReserva.isPresent()) {
			dbReserva.get().setVeiculo(reserva.getVeiculo());
			dbReserva.get().setEquipamentos(reserva.getEquipamentos());
			dbReserva.get().setDataInicial(reserva.getDataInicial());
			dbReserva.get().setDataCancelamento(reserva.getDataCancelamento());
			dbReserva.get().setDataFinal(reserva.getDataFinal());
			reservaRepository.save(dbReserva.get());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();		
	}
}
