package ru.numismatist.PreciousMetalCoins.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.numismatist.PreciousMetalCoins.models.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Integer> {
}
