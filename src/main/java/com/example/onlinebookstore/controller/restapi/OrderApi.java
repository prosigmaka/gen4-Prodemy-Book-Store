package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.CheckoutItemDto;
import com.example.onlinebookstore.model.dto.CheckoutOrderDto;
import com.example.onlinebookstore.model.dto.DetailPembayaranOrderDto;
import com.example.onlinebookstore.model.entity.CheckoutItem;
import com.example.onlinebookstore.model.entity.CheckoutOrder;
import com.example.onlinebookstore.model.entity.PesananStatus;
import com.example.onlinebookstore.repository.CheckoutItemRepository;
import com.example.onlinebookstore.repository.CheckoutOrderRepository;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.service.CheckoutOrderService;
import com.example.onlinebookstore.service.CheckoutService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderApi {
    @Autowired
    public CheckoutService checkoutService;
    @Autowired
    public CheckoutItemRepository checkoutItemRepository;
    @Autowired
    public CheckoutOrderRepository checkoutOrderRepository;
    @Autowired
    public KeranjangRepository keranjangRepository;
    @Autowired
    public ModelMapper modelMapper;

    @GetMapping("/all-order")
    public List<CheckoutOrderDto> getCheckoutOrderList() {
//        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAll();
        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAllOrderById();
        List<CheckoutOrderDto> checkoutOrderDtos =
                listCheckoutOrder.stream()
                        .map(checkoutOrder -> mapToDto(checkoutOrder))
                        .collect(Collectors.toList());
        return checkoutOrderDtos;
    }

    @GetMapping("/order/belum-bayar")
    public List<CheckoutOrderDto> getOrderListBelumBayar() {
//        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAll();
        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAllByStatusPesanan(PesananStatus.BELUM_BAYAR);
        List<CheckoutOrderDto> checkoutOrderDtos =
                listCheckoutOrder.stream()
                        .map(checkoutOrder -> mapToDto(checkoutOrder))
                        .collect(Collectors.toList());
        return checkoutOrderDtos;
    }

    @GetMapping("/order/diproses")
    public List<CheckoutOrderDto> getOrderListDiproses() {
//        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAll();
        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAllByStatusPesanan(PesananStatus.DIPROSES);
        List<CheckoutOrderDto> checkoutOrderDtos =
                listCheckoutOrder.stream()
                        .map(checkoutOrder -> mapToDto(checkoutOrder))
                        .collect(Collectors.toList());
        return checkoutOrderDtos;
    }

    @GetMapping("/order/berhasil")
    public List<CheckoutOrderDto> getOrderListBerhasil() {
//        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAll();
        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAllByStatusPesanan(PesananStatus.BERHASIL);
        List<CheckoutOrderDto> checkoutOrderDtos =
                listCheckoutOrder.stream()
                        .map(checkoutOrder -> mapToDto(checkoutOrder))
                        .collect(Collectors.toList());
        return checkoutOrderDtos;
    }

    @GetMapping("/detail-order/{idCO}")
    public List<CheckoutItemDto> getDetailCheckoutOrder(@PathVariable Integer idCO) {
        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllByIdOrder(idCO);
        List<CheckoutItemDto> checkoutItemDtos =
                checkoutItemList.stream()
                        .map(checkoutItem -> mapToCheckoutItemDto(checkoutItem))
                        .collect(Collectors.toList());

//        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAll();
//        Optional<CheckoutOrder> checkoutOrder= checkoutOrderRepository.findById(idCO);
//        CheckoutOrder checkoutOrderDetail = checkoutOrder.get();
//        CheckoutOrderDto checkoutOrderDto = modelMapper.map(checkoutOrderDetail, CheckoutOrderDto.class);

        return checkoutItemDtos;
    }

    private CheckoutItemDto mapToCheckoutItemDto(CheckoutItem checkoutItem) {
        CheckoutItemDto checkoutItemDto = modelMapper.map(checkoutItem, CheckoutItemDto.class);

        checkoutItemDto.setIdKeranjang(checkoutItem.getIdKeranjang());
        checkoutItemDto.setJudulBuku(checkoutItem.getKeranjang().getBook().getJudulBuku());
        checkoutItemDto.setKuantitasBuku(checkoutItem.getKeranjang().getKuantitasBuku());
        checkoutItemDto.setKategoriBuku(checkoutItem.getKeranjang().getBook().getCategory().getNamaKategori());
        checkoutItemDto.setHargaBuku(checkoutItem.getKeranjang().getBook().getHargaBuku());
        checkoutItemDto.setSubTotalHargaBuku(checkoutItem.getKeranjang().getSubTotalHargaBuku());

        checkoutItemDto.setIdOrder(checkoutItem.getIdOrder());
        checkoutItemDto.setTanggalCo(checkoutItem.getCheckoutOrder().getTanggalCo());
        checkoutItemDto.setTanggalOrder(checkoutItem.getCheckoutOrder().getTanggalOrder());
        checkoutItemDto.setTipePembayaran(checkoutItem.getCheckoutOrder().getTipePembayaran());
        checkoutItemDto.setBankPilihan(checkoutItem.getCheckoutOrder().getBankPilihan());
        checkoutItemDto.setTotalHargalCi(checkoutItem.getCheckoutOrder().getTotalHargalCi());
        checkoutItemDto.setBatasTanggalPembayaran(checkoutItem.getCheckoutOrder().getBatasTanggalPembayaran());
        checkoutItemDto.setStatusPesanan(checkoutItem.getCheckoutOrder().getStatusPesanan());


        checkoutItemDto.setId(checkoutItemDto.getId());

        return checkoutItemDto;
    }

    private CheckoutOrderDto mapToDto(CheckoutOrder checkoutOrder) {
        CheckoutOrderDto checkoutOrderDto = modelMapper.map(checkoutOrder, CheckoutOrderDto.class);

//        checkoutOrderDto.setTanggalCi(checkoutOrder.getTanggalCi());
//        checkoutOrderDto.setTotalHargalCi(checkoutOrder.getTotalHargalCi());
//        checkoutOrderDto.setTipePembayaran(checkoutOrder.getTipePembayaran());
//        checkoutOrderDto.setBatasTanggalPembayaran(checkoutOrder.getBatasTanggalPembayaran());
//        checkoutOrderDto.setStatusPesanan(checkoutOrder.getStatusPesanan());
//
//        checkoutOrderDto.setItems(checkoutOrder.getItems().get());

        checkoutOrderDto.setId(checkoutOrder.getId());

        return checkoutOrderDto;
    }


    @PostMapping(path = "/place-order")
    public @ResponseBody
    CheckoutOrder placeOrder(@RequestBody CheckoutItem checkoutItem) {
        CheckoutOrder checkoutOrderNew = checkoutService.placeOrder(checkoutItem);
        DetailPembayaranOrderDto detailPembayaranOrderDto = new DetailPembayaranOrderDto();
        checkoutOrderNew.setTipePembayaran(detailPembayaranOrderDto.getTipePembayaran());
        checkoutOrderNew.setBankPilihan(detailPembayaranOrderDto.getBankPilihan());
//    CheckoutOrder insertOrder(@RequestBody RequestListOrderDTO requestListOrderDTO) {
        return checkoutOrderRepository.save(checkoutOrderNew);

    }

//    @PostMapping(path = "/checkout")
//    public @ResponseBody
//    RequestOrderDTO checkoutItem(@PathVariable Integer idCart) {
//        Keranjang keranjang = keranjangRepository.findById(idCart).get();
//        RequestOrderDTO requestOrderDTO = new RequestOrderDTO();
//        requestOrderDTO.getCartsId().add(keranjang.getId());
//        return requestOrderDTO;
////        return checkoutOrderService.checkoutItem(requestOrderDTO);
//
//    }
}