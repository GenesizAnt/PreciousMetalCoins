package ru.numismatist.PreciousMetalCoins.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.numismatist.PreciousMetalCoins.dto.CoinXml;
import ru.numismatist.PreciousMetalCoins.models.Coin;
import ru.numismatist.PreciousMetalCoins.repositories.CoinRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true) // для методов которые что-то меняют поставить @Transactional
public class CoinService {

    private final CoinRepository coinRepository;

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
}
