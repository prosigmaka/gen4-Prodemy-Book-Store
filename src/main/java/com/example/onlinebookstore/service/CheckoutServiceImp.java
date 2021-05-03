package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.dto.CheckoutItemDto;
import com.example.onlinebookstore.model.dto.IdKeranjangDTO;
import com.example.onlinebookstore.model.dto.RequestListOrderDTO;
import com.example.onlinebookstore.model.entity.*;
import com.example.onlinebookstore.repository.CheckoutItemRepository;
import com.example.onlinebookstore.repository.CheckoutOrderRepository;
import com.example.onlinebookstore.repository.KeranjangRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CheckoutServiceImp implements CheckoutService {

    @Autowired
    CheckoutOrderRepository checkoutOrderRepository;

    @Autowired
    CheckoutItemRepository checkoutItemRepository;

    @Autowired
    KeranjangRepository keranjangRepository;

    @Autowired
    public ModelMapper modelMapper;


    @Override
    public List<CheckoutItemDto> checkout(RequestListOrderDTO requestListOrderDTO) {

        CheckoutOrder checkoutOrder = new CheckoutOrder();
        Date tanggalCi = new Date();
        long total = 0;

//        checkoutOrder.setTanggalCo(tanggalCo);
//        checkoutOrder.setBatasTanggalPembayaran(batasTanggalPembayaran);
        checkoutOrder.setStatusPesanan(PesananStatus.BELUM_BAYAR);
        CheckoutOrder checkoutOrderNC = checkoutOrderRepository.save(checkoutOrder);

        List<CheckoutItem> checkoutItemList = new ArrayList<CheckoutItem>();

        for (IdKeranjangDTO idKeranjangDTO : requestListOrderDTO.getIdKeranjangDTOList()) {
            Keranjang keranjang = keranjangRepository.findById(idKeranjangDTO.getId()).get();
            keranjang.setStatusKeranjang(ItemStatus.CHECKOUT);
            Keranjang keranjangSB = keranjangRepository.save(keranjang);
//            Keranjang k = keranjang.get();
            CheckoutItem checkoutItem = new CheckoutItem();
            checkoutItem.setKeranjang(keranjangSB);
            checkoutItem.setIdKeranjang(checkoutItem.getKeranjang().getId());
            checkoutItem.setCheckoutOrder(checkoutOrderNC);
            checkoutItem.setIdOrder(checkoutItem.getCheckoutOrder().getId());
            checkoutItem.setTanggalCi(tanggalCi);
            checkoutItemList.add(checkoutItem);
            total += keranjang.getSubTotalHargaBuku();
            checkoutItemRepository.save(checkoutItem);
        }

        checkoutOrderNC.setTotalHargalCi(total);
        checkoutOrderRepository.save(checkoutOrderNC);

//        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllIdOrderByTanggalCi();
        List<CheckoutItemDto> checkoutItemDtoList =
                checkoutItemList.stream()
                        .map(cI -> mapToDto(cI))
                        .collect(Collectors.toList());
        return checkoutItemDtoList;

    }

    private CheckoutItemDto mapToDto(CheckoutItem checkoutItem) {
        CheckoutItemDto checkoutItemDto = modelMapper.map(checkoutItem, CheckoutItemDto.class);

        checkoutItemDto.setJudulBuku(checkoutItem.getKeranjang().getBook().getJudulBuku());
        checkoutItemDto.setKuantitasBuku(checkoutItem.getKeranjang().getKuantitasBuku());
        checkoutItemDto.setKategoriBuku(checkoutItem.getKeranjang().getBook().getCategory().getNamaKategori());
        checkoutItemDto.setHargaBuku(checkoutItem.getKeranjang().getBook().getHargaBuku());
        checkoutItemDto.setSubTotalHargaBuku(checkoutItem.getKeranjang().getSubTotalHargaBuku());

        checkoutItemDto.setTanggalCo(checkoutItem.getCheckoutOrder().getTanggalCo());
        checkoutItemDto.setTotalHargalCi(checkoutItem.getCheckoutOrder().getTotalHargalCi());
        checkoutItemDto.setBatasTanggalPembayaran(checkoutItem.getCheckoutOrder().getBatasTanggalPembayaran());
        checkoutItemDto.setStatusPesanan(checkoutItem.getCheckoutOrder().getStatusPesanan());


        checkoutItemDto.setId(checkoutItemDto.getId());
        checkoutItemDto.setIdOrder(checkoutItem.getIdOrder());
        checkoutItemDto.setIdKeranjang(checkoutItem.getIdKeranjang());

        return checkoutItemDto;
    }

}
