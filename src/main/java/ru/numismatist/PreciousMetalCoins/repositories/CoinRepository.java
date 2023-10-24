package ru.numismatist.PreciousMetalCoins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.numismatist.PreciousMetalCoins.models.Coin;

import java.util.List;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Integer> {
    List<Coin> findByCatalogNumberStartingWith(String numberSectionDenominationMetal);
}
