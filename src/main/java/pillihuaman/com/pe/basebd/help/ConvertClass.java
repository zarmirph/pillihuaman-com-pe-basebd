package pillihuaman.com.pe.basebd.help;


import pillihuaman.com.pe.basebd.common.ProductStock;
import pillihuaman.com.pe.basebd.imagenProducer.File;
import pillihuaman.com.pe.basebd.imagenProducer.ImagenFile;
import pillihuaman.com.pe.basebd.product.Color;
import pillihuaman.com.pe.basebd.product.Product;
import pillihuaman.com.pe.basebd.product.Size;
import pillihuaman.com.pe.basebd.product.Stock;
import pillihuaman.com.pe.lib.request.*;
import pillihuaman.com.pe.lib.response.RespProduct;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ConvertClass {





    public static Product ProductDtoToProductTbl(ReqProduct request) {
        Product resp = new Product();
        resp.setDescription(request.getDescription());
        resp.setExpirationDate(request.getExpirationDate());
        resp.setIdImagen(request.getIdImagen());
        resp.setIdPrice(request.getIdPrice());
        // resp.setIdProduct(request.getIdProduct());
        resp.setIdSystem(request.getIdSystem());
        resp.setIdType(request.getIdType());
        resp.setIdUser(request.getIdUser());
        resp.setName(request.getName());
        return resp;

    }




    public static List<RespProduct> listProductoRespProduct(List<Product> lstproduct) {
        List<RespProduct> lstresp = new ArrayList<>();
        if (lstproduct != null && !lstproduct.isEmpty()) {
            for (Product product : lstproduct
            ) {
                RespProduct resp = new RespProduct();
                resp.setDescription(product.getDescription());
                resp.setExpirationDate(product.getExpirationDate());
                resp.setIdImagen(product.getIdImagen());
                resp.setIdPrice(product.getIdPrice());
                resp.setIdProduct(product.getId().toString());
                resp.setIdSystem(product.getIdSystem());
                resp.setIdType(product.getIdType());
                resp.setIdUser(product.getIdUser().toString());
                resp.setName(product.getName());
                lstresp.add(resp);

            }

        }
        return lstresp;

    }


    public static List<CorouselImage> respListImagenFileToImagenGeneral(List<ImagenFile> request) {
        List<CorouselImage> lst = new ArrayList<>();
        int count = 0;
        int clickCountMax = 0;
        int max = 0;
        for (ImagenFile im :
                request) {
            CorouselImage imr = new CorouselImage();
            clickCountMax = im.getMetadata().getClickCount();
            if (clickCountMax > max) {
                max = clickCountMax;
            }


            imr.setImageSrc(im.getId().toString());
            imr.setImageAlt(im.getFilename());
            imr.setImageCountainerToken(UUID.randomUUID().toString());
            imr.setImagetoken(UUID.randomUUID().toString());
            imr.setIndex(im.getMetadata().getIndex());
            imr.setIdDetail(im.getMetadata().getIdDetail().toString());
            count++;
            if (count == request.size()) {

                imr.setIndicators(true);
                imr.setFirstObject(max + "");
            } else {
                imr.setFirstObject(null);
                imr.setIndicators(false);
            }
            lst.add(imr);
        }
        return lst;

    }
/*
    public static RespControl listControlToRespControl(List<Control> request) {
        RespControl resp = new RespControl();
        List<ReqControl> lr = new ArrayList<>();
        for (Control co : request
        ) {
            ReqControl r = new ReqControl();
            r.set_id(co.getId());
            r.setId_user(co.getId_user());
            r.setIconClass(co.getIconClass());
            r.setDescription(co.getDescription());
            r.setIcono(co.getIcono());
            r.setIcono(co.getIcono());
            r.setStyleClass(co.getStyleClass());
            r.setIdMenu(co.getIdMenu());
            r.setStatus(co.getStatus());
            r.setIdPage(co.getIdPage());
            r.setIdSystem(co.getIdSystem());
            r.setIdCode(co.getIdCode());
            r.setText(co.getText());
            lr.add(r);
        }

        resp.setLstControles(lr);
        return resp;

    }*/
    public static ProductStock ProductStockRequestDtoToProductStock(ReqStock request) {
        ProductStock resp = new ProductStock();
        Size siz = new Size();
        Stock stock = new Stock();
        List<Size> lstSiz = new ArrayList<>();
        request.getSize();
        for (ReqSize s :
                request.getSize()) {
            Size sizs = new Size();
            sizs.setParameter(s.getParameter());
            List<Color> col = new ArrayList<>();
            for (ReqColor c :
                    s.getColor()) {
                Color cols = new Color();
                List<File> lst = new ArrayList<>();
                //cols.setFile(lst);
                //cols.setIdProduct(c.getIdProduct());
                col.add(cols);
                sizs.setColor(col);
            }
            lstSiz.add(sizs);
        }

        stock.setSize(lstSiz);
        resp.setCreationDate(request.getCreationDate());
        resp.setExpirationDate(request.getExpirationDate());
        Product product = new Product();
        //  product.setIdProduct(request.getIdProduct());
        // resp.setProduct(product);
        resp.setStock(stock);
        //resp.setStock();
        return resp;

    }



}
