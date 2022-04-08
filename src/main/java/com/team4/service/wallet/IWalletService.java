package com.team4.service.wallet;

import com.team4.model.Wallet;
import com.team4.service.IService;

import java.util.List;

public interface IWalletService extends IService<Wallet> {
    List<Wallet> selectAllWalletById (int idUser);
}
