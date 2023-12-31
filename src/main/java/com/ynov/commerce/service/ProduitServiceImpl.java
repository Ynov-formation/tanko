package com.ynov.commerce.service;

import com.ynov.commerce.dao.ProduitRepository;
import com.ynov.commerce.dto.ProduitDTO;
import com.ynov.commerce.entities.Produit;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

	private final ProduitRepository produitRepository;

	public ProduitServiceImpl(ProduitRepository produitRepository) {
		this.produitRepository = produitRepository;
	}

	@Override
	public Produit getProduitById(Long id) {
		return produitRepository.findById(id).orElse(null);
	}

	@Override
	public Produit saveProduit(Produit produit) {
		return produitRepository.save(produit);
	}

	@Override
	public Produit updateProduit(Produit produit) {
		return saveProduit(produit);
	}

	@Override
	public void deleteProduit(Long id) {
		produitRepository.deleteById(id);
	}

	@Override
	public List<ProduitDTO> getAllProduits() {
		return produitRepository.findAll().stream().map(produit -> ProduitDTO.builder()
				.id(produit.getId())
				.nom(produit.getNom())
				.description(produit.getDescription())
				.prix(produit.getPrix())
				.build()
		).toList();
	}
}
