package com.example.onlinebookstore.service;

import com.example.onlinebookstore.model.dto.*;
import com.example.onlinebookstore.model.entity.*;
import com.example.onlinebookstore.repository.CheckoutItemRepository;
import com.example.onlinebookstore.repository.CheckoutOrderRepository;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CheckoutOrderServiceImp implements CheckoutOrderService {

    @Autowired
    CheckoutOrderRepository checkoutOrderRepository;

    @Autowired
    CheckoutItemRepository checkoutItemRepository;

    @Autowired
    KeranjangRepository keranjangRepository;

    @Autowired
    public ModelMapper modelMapper;

//    @Autowired
//    public JSONObject jsonObject;


    @Override
    public Iterable<CheckoutOrder> getAllOrders() {
        return checkoutOrderRepository.findAll();
    }

    @Override
    public CheckoutOrder insertOrder(CheckoutItem checkoutItem) {
//    public CheckoutOrder insertOrder(RequestListOrderDTO requestListOrderDTO) {
//        Object list[]= requestListOrderDTO.getIdKeranjangDTOList().toArray();
//        Object keys[] = requestOrder.getKeranjang().keySet().toArray();

        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllIdOrderByTanggalCi();

        CheckoutOrder checkoutOrder = new CheckoutOrder();
        long total = 0;

        Date tanggalCo = new Date();

        Calendar cal = Calendar.getInstance();
        cal.setTime(tanggalCo);
        cal.add(Calendar.DATE, 1);

        Date batasTanggalPembayaran = cal.getTime();

        checkoutOrder.setTanggalCo(tanggalCo);
//        checkoutOrder.setTipePembayaran("Transfer Bank");
        checkoutOrder.setBatasTanggalPembayaran(batasTanggalPembayaran);
        checkoutOrder.setStatusPesanan(PesananStatus.BELUM_BAYAR);
//        CheckoutOrder checkoutOrderFinal= checkoutOrderRepository.save(checkoutOrder);

        for (int i = 0; i<checkoutItemList.size(); i++){
//        for (CheckoutItem checkoutItemLoop : checkoutItemList()) {
//        for (IdKeranjangDTO idKeranjangDTO : requestListOrderDTO.getIdKeranjangDTOList()) {
//            System.out.println(idKeranjangDTO.getId());

            CheckoutItem checkoutItemId = checkoutItemList.get(i);
            Optional<Keranjang> keranjang = keranjangRepository.findById(checkoutItemId.getIdKeranjang());
            Keranjang k = keranjang.get();
            k.setStatusKeranjang(ItemStatus.PAID);
            Keranjang keranjangNewStatus = keranjangRepository.save(k);
            checkoutItemId.setKeranjang(keranjangNewStatus);
            checkoutItemRepository.save(checkoutItemId);

//            CheckoutItem checkoutItemId = checkoutItemRepository.findById(checkoutItemList.get(i));
//            CheckoutItem checkoutItem = checkoutItemRepository.findByIdKeranjang(idKeranjangDTO.getId());
//            checkoutItem.getCheckoutOrder().setTanggalCo(tanggalCo);
//            checkoutItem.setId(checkoutItem.getId());
//            checkoutItem.setIdKeranjang(checkoutItem.getKeranjang().getId());
//            checkoutItem.setTanggalCi(checkoutItem.getTanggalCi());
            checkoutOrder.getItems().add(checkoutItemId);


            total += checkoutItemId.getKeranjang().getSubTotalHargaBuku();

//            Keranjang k = keranjang.get();
//            CheckoutItem checkoutItem = new CheckoutItem();
//            checkoutItem.setKeranjang(keranjang);
//            checkoutItem.setIdKeranjang(checkoutItem.getKeranjang().getId());
//            checkoutItem.setTanggalCi(tanggalCi);
//            checkoutItemList.add(checkoutItem);
//            total += keranjang.getSubTotalHargaBuku();
//            checkoutItemRepository.save(checkoutItem);
        }
        checkoutOrder.setTotalHargalCi(total);
        return checkoutOrder;


//        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllIdOrderByTanggalCi();
//        Object list[] = checkoutItemList.toArray();
//
//        int ci_index[] = new int[list.length];
//        for (int i = 0; i < ci_index.length; i++) {
//            ci_index[i] = (Integer) checkoutItemList.toArray()[i];
//        }
//
//        CheckoutOrder checkoutOrder = new CheckoutOrder();
//        long total = 0;
//        Date tanggalCo = new Date();
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(tanggalCo);
//        cal.add(Calendar.DATE, 1);
//
//        Date batasTanggalPembayaran = cal.getTime();
//
//        for (int i = 0; i < ci_index.length; i++) {
////            Keranjang keranjang = keranjangRepository.findById(keranjang_index[i]).get();
//            Optional<CheckoutItem> optionalCheckoutItem = checkoutItemRepository.findById(ci_index[i]);
//            CheckoutItem ci = optionalCheckoutItem.get();
//            total += ci.getKeranjang().getSubTotalHargaBuku();
//            checkoutOrder.getItems().add(ci);
//        }
//        checkoutOrder.setTanggalCo(tanggalCo);
//        checkoutOrder.setTotalHargalCi(total);
//        checkoutOrder.setTipePembayaran("Transfer Bank");
//        checkoutOrder.setBatasTanggalPembayaran(batasTanggalPembayaran);
//        checkoutOrder.setStatusPesanan(PesananStatus.BELUM_BAYAR);
//        return checkoutOrderRepository.save(checkoutOrder);
    }


    @Override
    public List<CheckoutItemDto> checkoutItem(RequestListOrderDTO requestListOrderDTO) {
        System.out.println(requestListOrderDTO.getIdKeranjangDTOList());

//        ArrayList<String> listId = new ArrayList<>();
//        RequestOrderDTO requestOrderDTO = modelMapper.map(requestListOrderDTO,RequestOrderDTO.class);
//        Integer.parseInt(String.valueOf(requestOrderDTO.getIdKeranjangDTOList()));
//        Object list[]= requestListOrderDTO.getIdKeranjangDTOList().toArray();


        List<CheckoutItem> checkoutItemList = new ArrayList<CheckoutItem>();
        Date tanggalCi = new Date();
        long total = 0;
        for (IdKeranjangDTO idKeranjangDTO : requestListOrderDTO.getIdKeranjangDTOList()) {
//            System.out.println(idKeranjangDTO.getId());
            Keranjang keranjang = keranjangRepository.findById(idKeranjangDTO.getId()).get();
            keranjang.setStatusKeranjang(ItemStatus.CHECKOUT);
            Keranjang keranjangSB = keranjangRepository.save(keranjang);
//            Keranjang k = keranjang.get();
            CheckoutItem checkoutItem = new CheckoutItem();
            checkoutItem.setKeranjang(keranjangSB);
            checkoutItem.setIdKeranjang(checkoutItem.getKeranjang().getId());
            checkoutItem.setTanggalCi(tanggalCi);
            checkoutItemList.add(checkoutItem);
            total += keranjang.getSubTotalHargaBuku();
            checkoutItemRepository.save(checkoutItem);
        }


//        List<CheckoutItem> checkoutItemListNew = checkoutItemRepository.findAllIdOrderByTanggalCi();
        List<CheckoutItemDto> checkoutItemDtoList =
                checkoutItemList.stream()
                        .map(cI -> mapToDto(cI))
                        .collect(Collectors.toList());
        return checkoutItemDtoList;


//        Object list[]= requestListOrderDTO.getIdKeranjangDTOList().toArray();
//        int keranjang_index[] = new int[list.length];
//
//        for (int i = 0; i < keranjang_index.length; i++){

//            listId.add(String.valueOf(requestListOrderDTO.getIdKeranjangDTOList().get(i)));

//            keranjang_index[i] = (int) requestListOrderDTO.getIdKeranjangDTOList().toArray()[i];
//        }

//        Object[] arr_id = listId.toArray();
//        String[] keranjangId = new String[arr_id.length];

//        CheckoutItem checkoutItem = new CheckoutItem();

//        long total = 0;
//        for (int i = 0; i < keranjang_index.length; i++){
//            keranjang_index[i] = (Integer) requestListOrderDTO.getIdKeranjangDTOList().toArray()[i];
//            Keranjang keranjang = keranjangRepository.findById(keranjang_index[i]).get();
//            keranjangId[i] = String.valueOf(arr_id[i]);

//            Optional<Keranjang> keranjang = keranjangRepository.findById(keranjang_index[i]);
//            Keranjang k = keranjang.get();
//            checkoutItem.setKeranjang(k);
//            checkoutItem.setTanggalCi(new Date());
//            total += k.getSubTotalHargaBuku();
//            checkoutItemRepository.save(checkoutItem);

//            requestOrderDTO.getCartsId().add(keranjang.getId());
//            List <CheckoutItem> checkoutItemList = new ArrayList<>();
//            checkoutItemList.add(checkoutItem);
//        }

//        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllOrderByTanggalCiAndId();
//        List<CheckoutItemDto> checkoutItemDtoList =
//                checkoutItemList.stream()
//                        .map(cI -> mapToDto(checkoutItem))
//                        .collect(Collectors.toList());
//        return checkoutItemDtoList;

//        CheckoutItemDto checkoutItemDto = new CheckoutItemDto();
//        checkoutItemDto.setJudulBuku(checkoutItem.getKeranjang().getBook().getJudulBuku());
//        checkoutItemDto.setKategoriBuku(checkoutItem.getKeranjang().getBook().getCategory().getNamaKategori());
//        checkoutItemDto.setHargaBuku(checkoutItem.getKeranjang().getBook().getHargaBuku());
//        checkoutItemDto.setKuantitasBuku(checkoutItem.getKeranjang().getKuantitasBuku());
//        checkoutItemDto.setSubTotalHargaBuku(checkoutItemDto.getSubTotalHargaBuku());
//
//        return checkoutItemDto;
    }


    private CheckoutItemDto mapToDto(CheckoutItem checkoutItem) {
        CheckoutItemDto checkoutItemDto = modelMapper.map(checkoutItem, CheckoutItemDto.class);

        checkoutItemDto.setJudulBuku(checkoutItem.getKeranjang().getBook().getJudulBuku());
        checkoutItemDto.setKuantitasBuku(checkoutItem.getKeranjang().getKuantitasBuku());
        checkoutItemDto.setKategoriBuku(checkoutItem.getKeranjang().getBook().getCategory().getNamaKategori());
        checkoutItemDto.setHargaBuku(checkoutItem.getKeranjang().getBook().getHargaBuku());
        checkoutItemDto.setSubTotalHargaBuku(checkoutItem.getKeranjang().getSubTotalHargaBuku());

        checkoutItemDto.setId(checkoutItemDto.getId());

        return checkoutItemDto;
    }

}
