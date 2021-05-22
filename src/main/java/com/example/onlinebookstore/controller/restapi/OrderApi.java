package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.CheckoutItemDto;
import com.example.onlinebookstore.model.dto.CheckoutOrderDto;
import com.example.onlinebookstore.model.dto.DateSearcherDto;
import com.example.onlinebookstore.model.entity.CheckoutItem;
import com.example.onlinebookstore.model.entity.CheckoutOrder;
import com.example.onlinebookstore.model.entity.PesananStatus;
import com.example.onlinebookstore.repository.CheckoutItemRepository;
import com.example.onlinebookstore.repository.CheckoutOrderRepository;
import com.example.onlinebookstore.repository.KeranjangRepository;
import com.example.onlinebookstore.service.CheckoutService;
import com.example.onlinebookstore.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order")
public class OrderApi {
    @Autowired
    public UserService userService;
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
        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAllByStatusPesananAndIdCostumer(userService.idCustomerLogIn(), PesananStatus.BELUM_BAYAR);
        List<CheckoutOrderDto> checkoutOrderDtos =
                listCheckoutOrder.stream()
                        .map(checkoutOrder -> mapToDtoCO(checkoutOrder))
                        .collect(Collectors.toList());
        return checkoutOrderDtos;
    }

    @GetMapping("/order/belum-bayar")
    public List<CheckoutOrderDto> getOrderListBelumBayar() {
//        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAll();
        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAllByIdCostumerAndStatusPesanan(userService.idCustomerLogIn(), PesananStatus.BELUM_BAYAR);
        List<CheckoutOrderDto> checkoutOrderDtos =
                listCheckoutOrder.stream()
                        .map(checkoutOrder -> mapToDtoCO(checkoutOrder))
                        .collect(Collectors.toList());
        return checkoutOrderDtos;
    }

    @GetMapping("/order/diproses")
    public List<CheckoutOrderDto> getOrderListDiproses() {
//        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAll();
        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAllByIdCostumerAndStatusPesanan(userService.idCustomerLogIn(), PesananStatus.DIPROSES);
        List<CheckoutOrderDto> checkoutOrderDtos =
                listCheckoutOrder.stream()
                        .map(checkoutOrder -> mapToDtoCO(checkoutOrder))
                        .collect(Collectors.toList());
        return checkoutOrderDtos;
    }

    @GetMapping("/order/berhasil")
    public List<CheckoutOrderDto> getOrderListBerhasil() {
//        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAll();
        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAllByIdCostumerAndStatusPesanan(userService.idCustomerLogIn(), PesananStatus.BERHASIL);
        List<CheckoutOrderDto> checkoutOrderDtos =
                listCheckoutOrder.stream()
                        .map(checkoutOrder -> mapToDtoCO(checkoutOrder))
                        .collect(Collectors.toList());
        return checkoutOrderDtos;
    }

    @PostMapping("/find-order/between-date")
    public List<CheckoutOrderDto> getOrderListByDate(@RequestBody DateSearcherDto dateSearcherDto) {
//        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAll();
        List<CheckoutOrder> listCheckoutOrder = checkoutOrderRepository.findAllByIdCostumerAndTglOrderBetweenOrderById(userService.idCustomerLogIn(), dateSearcherDto.getStartDate(),dateSearcherDto.getEndDate());
        List<CheckoutOrderDto> checkoutOrderDtos =
                listCheckoutOrder.stream()
                        .map(checkoutOrder -> mapToDtoCO(checkoutOrder))
                        .collect(Collectors.toList());
        return checkoutOrderDtos;
    }

    @GetMapping("/co-popage")
    public CheckoutOrderDto checkoutOrderDataForPOPage(){
        CheckoutOrder getCheckoutOrder = checkoutOrderRepository.findAllByIdCostumerAndTanggalOrderById(userService.idCustomerLogIn());

        CheckoutOrderDto checkoutOrderDto = modelMapper.map(getCheckoutOrder, CheckoutOrderDto.class);
        checkoutOrderDto.setId(getCheckoutOrder.getId());

        return checkoutOrderDto;

    }

    @GetMapping("/ci-popage")
    public List<CheckoutItemDto> checkoutItemListForPOPage(){
        CheckoutOrder getCheckoutOrder = checkoutOrderRepository.findAllByIdCostumerAndTanggalOrderById(userService.idCustomerLogIn());
        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllByIdOrder(getCheckoutOrder.getId());

        List<CheckoutItemDto> checkoutItemDtoList =
                checkoutItemList.stream()
                        .map(cI -> mapToCheckoutItemDto(cI))
                        .collect(Collectors.toList());
        return checkoutItemDtoList;

    }

    @GetMapping("/co-detailorderpage/{idCO}")
    public CheckoutOrderDto getCheckoutOrderForODPage(@PathVariable Integer idCO) {
        CheckoutOrder checkoutOrder = checkoutOrderRepository.findAllByIdAndIdCostumer(userService.idCustomerLogIn(), idCO);

        CheckoutOrderDto checkoutOrderDto = modelMapper.map(checkoutOrder, CheckoutOrderDto.class);
        checkoutOrderDto.setId(checkoutOrder.getId());

        return checkoutOrderDto;
    }

    @GetMapping("/ci-detailorderpage/{idCO}")
    public List<CheckoutItemDto> getCheckoutItemListForODPage(@PathVariable Integer idCO) {
        CheckoutOrder checkoutOrder = checkoutOrderRepository.findAllByIdAndIdCostumer(userService.idCustomerLogIn(), idCO);
        List<CheckoutItem> checkoutItemList = checkoutItemRepository.findAllByIdOrder(checkoutOrder.getId());
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
//        checkoutItemDto.setTanggalCo(checkoutItem.getCheckoutOrder().getTanggalCo());
//        checkoutItemDto.setTanggalOrder(checkoutItem.getCheckoutOrder().getTanggalOrder());
//        checkoutItemDto.setTipePembayaran(checkoutItem.getCheckoutOrder().getTipePembayaran());
//        checkoutItemDto.setBankPilihan(checkoutItem.getCheckoutOrder().getBankPilihan());
//        checkoutItemDto.setTotalHargalCi(checkoutItem.getCheckoutOrder().getTotalHargalCi());
//        checkoutItemDto.setBatasTanggalPembayaran(checkoutItem.getCheckoutOrder().getBatasTanggalPembayaran());
//        checkoutItemDto.setStatusPesanan(checkoutItem.getCheckoutOrder().getStatusPesanan());


        checkoutItemDto.setId(checkoutItemDto.getId());

        return checkoutItemDto;
    }

    private CheckoutOrderDto mapToDtoCO(CheckoutOrder checkoutOrder) {
        CheckoutOrderDto checkoutOrderDto = modelMapper.map(checkoutOrder, CheckoutOrderDto.class);

//        checkoutOrderDto.setTanggalCi(checkoutOrder.getTanggalCi());
//        checkoutOrderDto.setTotalHargalCi(checkoutOrder.getTotalHargalCi());
//        checkoutOrderDto.setTipePembayaran(checkoutOrder.getTipePembayaran());
//        checkoutOrderDto.setBatasTanggalPembayaran(checkoutOrder.getBatasTanggalPembayaran());
//        checkoutOrderDto.setStatusPesanan(checkoutOrder.getStatusPesanan());
//
//        checkoutOrderDto.setItems(checkoutOrder.getItems().get());

        checkoutOrderDto.setIdCostumer(checkoutOrder.getIdCostumer());
        checkoutOrderDto.setId(checkoutOrder.getId());

        return checkoutOrderDto;
    }


    @PostMapping(path = "/place-order")
    public @ResponseBody
    CheckoutOrder placeOrder(@RequestBody CheckoutItem checkoutItem) {
        CheckoutOrder checkoutOrderNew = checkoutService.placeOrder(checkoutItem);
//        DetailPembayaranOrderDto detailPembayaranOrderDto = new DetailPembayaranOrderDto();
        CheckoutOrderDto checkoutOrderDto = new CheckoutOrderDto();
        checkoutOrderNew.setTipePembayaran(checkoutOrderDto.getTipePembayaran());
        checkoutOrderNew.setBankPilihan(checkoutOrderDto.getBankPilihan());

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