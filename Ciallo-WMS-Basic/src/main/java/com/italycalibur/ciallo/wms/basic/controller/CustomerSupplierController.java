package com.italycalibur.ciallo.wms.basic.controller;

import cn.idev.excel.FastExcel;
import com.italycalibur.ciallo.wms.basic.service.ICustomerSupplierService;
import com.italycalibur.ciallo.wms.core.common.Result;
import com.italycalibur.ciallo.wms.core.constants.CommonConstants;
import com.italycalibur.ciallo.wms.core.models.entity.CustomerSupplier;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @description: 客商表 控制器
 * @author dhr
 * @date 2025-03-07 14:01:27
 * @version 1.0
 */
@Tag(name = "客商管理")
@RestController
@RequestMapping("/basic/customerSupplier")
@RequiredArgsConstructor
public class CustomerSupplierController {
    @Resource
    private ICustomerSupplierService customerSupplierService;

    /**
     * Excel读取功能（导入）
     * @param file 导入文件
     * @return Result<String>
     */
    @Operation(summary = "导入客商信息")
    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("请选择一个文件上传！");
        }
        try {
            return customerSupplierService.upload(file);
        } catch (IOException e) {
            return Result.error("文件处理失败！");
        }
    }

    /**
     * Excel写入功能（导出）
     * @param response 响应对象
     * @throws IOException 异常
     */
    @Operation(summary = "导出客商信息")
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException {
        ClassPathResource resource = new ClassPathResource("template/客商信息导出模板.xlsx");
        File file = resource.getFile();
        response.setContentType(CommonConstants.EXPORT_CONTENT_TYPE);
        response.setCharacterEncoding(CommonConstants.DEFAULT_CHARSET_ENCODING);
        String fileName = URLEncoder.encode("客商信息导出模板", StandardCharsets.UTF_8);
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        // 写入数据
        FastExcel.write(response.getOutputStream(), CustomerSupplier.class)
                .withTemplate(file)
                .sheet()
                .doFill(customerSupplierService.list());
    }
}
