package ru.numismatist.PreciousMetalCoins.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.numismatist.PreciousMetalCoins.services.CoinService;

@Controller
@RequestMapping("/coins")
public class GetCoinController {

    private final CoinService coinService;

    public GetCoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping("/get/{id}")
    public void getCoin(@PathVariable int id) {
        System.out.println(coinService.getCoinByIndex(id).getCost());
    }
}
