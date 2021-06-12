package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.dto.CheckoutItemDto;
import com.example.onlinebookstore.model.dto.CostumerLoginDTO;
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
    UserService userService;

    @Autowired
    CheckoutOrderRepository checkoutOrderRepository;

    @Autowired
    CheckoutItemRepository checkoutItemRepository;

    @Autowired
    KeranjangRepository keranjangRepository;

    @Autowired
    public ModelMapper modelMapper;


    @Override
    public CheckoutOrder checkoutOrder(RequestListOrderDTO requestListOrderDTO, CostumerLoginDTO costumerLoginDTO) {
        CheckoutOrder checkoutOrder = new CheckoutOrder();
        Date tanggalCheckout = new Date();
        long total = 0;

        checkoutOrder.setTanggalCo(tanggalCheckout);
//        checkoutOrder.setBatasTanggalPembayaran(batasTanggalPembayaran);
        checkoutOrder.setStatusPesanan(PesananStatus.BELUM_ORDER);
//        checkoutOrder.setIdCostumer(userService.idCustomerLogIn());
        checkoutOrder.setIdCostumer(costumerLoginDTO.getId());
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
            checkoutItem.setTanggalCi(tanggalCheckout);
            checkoutItemList.add(checkoutItem);
            total += keranjang.getSubTotalHargaBuku();
            checkoutItemRepository.save(checkoutItem);
        }

        checkoutOrderNC.setTotalHargalCi(total);

        return checkoutOrderRepository.save(checkoutOrderNC);
    }

//    @Override
//    public List<CheckoutItemDto> checkout(RequestListOrderDTO requestListOrderDTO) {
//
//        CheckoutOrder checkoutOrder = new CheckoutOrder();
//        Date tanggalCheckout = new Date();
//        long total = 0;
//
//        checkoutOrder.setTanggalCo(tanggalCheckout);
////        checkoutOrder.setBatasTanggalPembayaran(batasTanggalPembayaran);
//        checkoutOrder.setStatusPesanan(PesananStatus.BELUM_BAYAR);
//        CheckoutOrder checkoutOrderNC = checkoutOrderRepository.save(checkoutOrder);
//
//        List<CheckoutItem> checkoutItemList = new ArrayList<CheckoutItem>();
//
//        for (IdKeranjangDTO idKeranjangDTO : requestListOrderDTO.getIdKeranjangDTOList()) {
//            Keranjang keranjang = keranjangRepository.findById(idKeranjangDTO.getId()).get();
//            keranjang.setStatusKeranjang(ItemStatus.CHECKOUT);
//            Keranjang keranjangSB = keranjangRepository.save(keranjang);
////            Keranjang k = keranjang.get();
//            CheckoutItem checkoutItem = new CheckoutItem();
//            checkoutItem.setKeranjang(keranjangSB);
//            checkoutItem.setIdKeranjang(checkoutItem.getKeranjang().getId());
//            checkoutItem.setCheckoutOrder(checkoutOrderNC);
//            checkoutItem.setIdOrder(checkoutItem.getCheckoutOrder().getId());
//            checkoutItem.setTanggalCi(tanggalCheckout);
//            checkoutItemList.add(checkoutItem);
//            total += keranjang.getSubTotalHargaBuku();
//            checkoutItemRepository.save(checkoutItem);
//        }
//
//        checkoutOrderNC.setTotalHargalCi(total);
//        checkoutOrderRepository.save(checkoutOrderNC);
//
////        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllIdOrderByTanggalCi();
//        List<CheckoutItemDto> checkoutItemDtoList =
//                checkoutItemList.stream()
//                        .map(cI -> mapToDto(cI))
//                        .collect(Collectors.toList());
//        return checkoutItemDtoList;
//
//    }

    @Override
    public CheckoutOrder placeOrder(CostumerLoginDTO costumerLoginDTO) {

//        CheckoutOrder checkoutOrder = checkoutOrderRepository.findAllByIdCostumerAndTanggalCoOrderById(userService.idCustomerLogIn());
        CheckoutOrder checkoutOrder = checkoutOrderRepository.findAllByIdCostumerAndTanggalCoOrderById(costumerLoginDTO.getId());
        List<CheckoutItem> checkoutItemList =checkoutItemRepository.findAllByIdOrderAndTanggalCiOrderById(checkoutOrder.getId());

        Date tanggalOrder = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggalOrder);
        cal.add(Calendar.DATE, 1);

        Date batasTanggalPembayaran = cal.getTime();

        checkoutOrder.setTanggalOrder(tanggalOrder);
        checkoutOrder.setBatasTanggalPembayaran(batasTanggalPembayaran);
        checkoutOrder.setStatusPesanan(PesananStatus.BELUM_BAYAR);
        checkoutOrder.setTipePembayaran("Bank Transfer");
        checkoutOrder.setBankPilihan("Bank Syariah Indonesia");

        for (int i = 0; i<checkoutItemList.size(); i++){
            CheckoutItem checkoutItemId = checkoutItemList.get(i);
            Optional<Keranjang> keranjang = keranjangRepository.findById(checkoutItemId.getIdKeranjang());
            Keranjang k = keranjang.get();
            k.setStatusKeranjang(ItemStatus.ORDERED);
            keranjangRepository.save(k);
        }
        return checkoutOrder;
    }



}
