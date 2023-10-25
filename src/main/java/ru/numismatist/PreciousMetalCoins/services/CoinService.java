package ru.numismatist.PreciousMetalCoins.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.numismatist.PreciousMetalCoins.models.Coin;
import ru.numismatist.PreciousMetalCoins.repositories.CoinRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CoinService {

    private final CoinRepository coinRepository;

    @Autowired
    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public Coin getCoinByIndex(int id) {
        Optional<Coin> byId = coinRepository.findById(id);
        return byId.orElse(null);
    }

    public List<Coin> findAllCoin() {
        return coinRepository.findAll();
    }

    @Transactional
    public void addNewCoin(Coin newCoin) {
        coinRepository.save(newCoin);
    }

    @Transactional
    public void deleteCoinById(int id) {
        coinRepository.deleteById(id);
    }

    @Transactional
    public void updateCoin(int id, Coin patchCoin) {
        patchCoin.setId(id);
        coinRepository.save(patchCoin);
    }

    public List<Coin> numberSectionDenominationMetal(String numberSectionDenominationMetal) {
        return coinRepository.findByCatalogNumberStartingWith(numberSectionDenominationMetal);
    }
}
