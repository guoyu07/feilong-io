/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.io;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.json.jsonlib.JsonUtil;

/**
 * The Class FilenameUtilTest.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.4.0
 */
public class FilenameUtilTest{

    /** The Constant LOGGER. */
    private static final Logger LOGGER    = LoggerFactory.getLogger(FilenameUtilTest.class);

    /** <code>{@value}</code>. */
    private static String       FILE_NAME = "F:/pie2.png";

    /**
     * Gets the file postfix name lower case.
     * 
     */
    @Test
    public void tstGetFilePostfixNameLowerCase(){
        assertEquals("a", FilenameUtil.getExtensionLowerCase("a.A"));
    }

    /**
     * Test get extension.
     */
    @Test
    public void testGetExtension(){
        assertEquals("png", FilenameUtil.getExtension(FILE_NAME));
        LOGGER.debug(FILE_NAME.substring(FILE_NAME.lastIndexOf(".")));
        LOGGER.debug(FILE_NAME.substring(FILE_NAME.lastIndexOf("\\") + 1));
    }

    /**
     * Checks for postfix name.
     */
    @Test
    public void hasPostfixName(){
        FILE_NAME = "a";
        LOGGER.debug(FilenameUtil.hasExtension(FILE_NAME) + "");
    }

    /**
     * Test get new file name.
     */
    @Test
    public void testGetNewFileName(){
        assertEquals("F:/pie2.gif", FilenameUtil.getNewFileName(FILE_NAME, "gif"));
    }

    /**
     * Test get file name.
     */
    @Test
    public void testGetFileName(){
        LOGGER.debug(FilenameUtil.getFileName(FILE_NAME));
    }

    /**
     * Test get file pre name.
     */
    @Test
    @Ignore
    public void testGetFilePreName(){
        assertEquals("F:/pie2", FilenameUtil.getFilePreName(FILE_NAME));
        assertEquals("F:/pie2", FilenameUtils.getBaseName(FILE_NAME));
    }

    /**
     * 常用图片格式.
     * 
     * @deprecated 表述不清晰,将会重构
     */
    @Deprecated
    private static final String[] COMMON_IMAGES = { "gif", "bmp", "jpg", "png" };

    /**
     * 上传的文件是否是常用图片格式.
     * 
     * @param fileName
     *            文件名称,可以是全路径 ,也可以是 部分路径,会解析取到后缀名
     * @return 上传的文件是否是常用图片格式
     */
    public static boolean isCommonImage(String fileName){
        return isInAppointTypes(fileName, COMMON_IMAGES);
    }

    /**
     * 上传的文件是否在指定的文件类型里面.
     * 
     * @param fileName
     *            文件名称
     * @param appointTypes
     *            指定的文件类型数组
     * @return 上传的文件是否在指定的文件类型里面
     */
    // XXX 忽视大小写
    public static boolean isInAppointTypes(String fileName,String[] appointTypes){
        String filePostfixName = FilenameUtil.getExtension(fileName);
        return ArrayUtils.contains(appointTypes, filePostfixName);
    }

    /**
     * Test get file top parent name.
     */
    @Test
    public void testGetFileTopParentName(){
        assertEquals("E:/", FilenameUtil.getFileTopParentName("E:/"));
        assertEquals(
                        "mp2-product",
                        FilenameUtil.getFileTopParentName(
                                        "mp2-product\\mp2-product-impl\\src\\main\\java\\com\\baozun\\mp2\\rpc\\impl\\item\\repo\\package-info.java"));
        assertEquals(
                        "mp2-product",
                        FilenameUtil.getFileTopParentName(
                                        "mp2-product\\mp2-product-impl\\src\\..\\java\\com\\baozun\\mp2\\rpc\\impl\\item\\repo\\package-info.java"));
        assertEquals("package-info.java", FilenameUtil.getFileTopParentName("package-info.java"));
    }

    /**
     * Test get file top parent name1.
     */
    @Test
    public void testGetFileTopParentName1(){
        String remoteDirectory = "/home/sftp-speedo/test/aa/bbb/ccc/ddd/201606160101/";
        List<String> list = FilenameUtil.getParentPathList(remoteDirectory);
        LOGGER.debug(JsonUtil.format(list));

        Collections.reverse(list);
        LOGGER.debug(JsonUtil.format(list));
    }

}
