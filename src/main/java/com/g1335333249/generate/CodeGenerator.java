package com.g1335333249.generate;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author guanpeng
 * @date 2019-09-17 14:30
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help);
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 全局配置
     *
     * @param projectPath
     * @return
     */
    private static GlobalConfig getGlobalConfig(String projectPath) {
        GlobalConfig globalConfig = new GlobalConfig();
        // 生成文件的输出目录【默认 D 盘根目录】
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        // 是否覆盖已有文件
        globalConfig.setFileOverride(false);
        // 是否打开输出目录
        globalConfig.setOpen(false);
        // 开发人员
        globalConfig.setAuthor("guanpeng");
        // 是否在xml中添加二级缓存配置
        globalConfig.setEnableCache(false);
        // 实体属性 Swagger2 注解
        globalConfig.setSwagger2(false);
        // 开启 Kotlin 模式
        globalConfig.setKotlin(false);
        // 开启 ActiveRecord 模式
        globalConfig.setActiveRecord(false);
        // 开启 BaseResultMap
        globalConfig.setBaseResultMap(false);
        // 时间类型对应策略
        globalConfig.setDateType(DateType.ONLY_DATE);
        // 开启 baseColumnList
        globalConfig.setBaseColumnList(false);
        // 指定生成的主键的ID类型
        // globalConfig.setIdType(IdType.AUTO);
        return globalConfig;
    }

    /**
     * 数据源配置
     *
     * @return
     */
    public static DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl(
                "jdbc:mysql://localhost/jd_cloud_wifi?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        return dataSourceConfig;
    }

    /**
     * 包配置
     *
     * @return
     */
    public static PackageConfig getPackageConfig() {
        PackageConfig packageConfig = new PackageConfig();
        // 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        packageConfig.setParent("com.g1335333249");
        // 父包模块名
        packageConfig.setModuleName("");
        // Entity包名
        packageConfig.setEntity("entity");
        // Service包名
        packageConfig.setService("service");
        // Service Impl包名
        packageConfig.setServiceImpl("service.impl");
        // Mapper包名
        packageConfig.setMapper("mapper");
        // Mapper XML包名
        packageConfig.setXml("mapper.xml");
        // Controller包名
        packageConfig.setController("controller");
        return packageConfig;
    }

    /**
     * 自定义配置
     *
     * @return
     */
    public static InjectionConfig getInjectionConfig(String projectPath,
                                                     PackageConfig packageConfig) {

        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + packageConfig.getModuleName() + "/" + tableInfo.getEntityName()
                        + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
         * cfg.setFileCreate(new IFileCreate() {
         *
         * @Override public boolean isCreate(ConfigBuilder configBuilder, FileType
         * fileType, String filePath) { // 判断自定义文件夹是否需要创建 checkDir("调用默认方法创建的目录"); return
         * false; } });
         */
        injectionConfig.setFileOutConfigList(focList);
        return injectionConfig;
    }

    public static TemplateConfig getTemplateConfig() {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();
        templateConfig.setXml(null);
        return templateConfig;
    }

    /**
     * 策略配置
     *
     * @param packageConfig
     * @return
     */
    public static StrategyConfig getStrategyConfig(PackageConfig packageConfig) {
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 是否大写命名
        strategyConfig.setCapitalMode(false);
        // 是否跳过视图
        strategyConfig.setSkipView(false);
        // 名称转换
        // strategyConfig.setNameConvert();
        // 数据库表映射到实体的命名策略
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 表字段映射到实体的命名策略
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        // 表前缀
        // strategyConfig.setTablePrefix("");
        // 字段前缀
        // strategyConfig.setFieldPrefix("");
        // 自定义继承的Entity类全称，带包名
        // strategyConfig.setSuperEntityClass("com.hffss.entity.common.BaseEntity");
        // 自定义基础的Entity类，公共字段 写于父类中的公共字段
        // strategyConfig.setSuperEntityColumns("id");
        // 自定义继承的Mapper类全称，带包名
        // strategyConfig.setSuperMapperClass(ConstVal.SUPER_MAPPER_CLASS);
        // 自定义继承的Service类全称，带包名
        // strategyConfig.setSuperServiceClass(ConstVal.SUPER_SERVICE_CLASS);
        // 自定义继承的ServiceImpl类全称，带包名
        // strategyConfig.setSuperServiceImplClass(ConstVal.SUPER_SERVICE_IMPL_CLASS);
        // 自定义继承的Controller类全称，带包名
        // strategyConfig.setSuperControllerClass("com.hffss.controllor.common.BaseController");
        // 实体是否生成 serialVersionUID
        strategyConfig.setEntitySerialVersionUID(true);
        // 【实体】是否生成字段常量（默认 false）
        strategyConfig.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        strategyConfig.setEntityBuilderModel(true);
        // 【实体】是否为lombok模型（默认 false）
        strategyConfig.setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀（默认 false）比如 : 数据库字段名称 : 'is_xxx',类型为 : tinyint.
        // 在映射实体的时候则会去掉is,在实体类中映射最终结果为 xxx
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(false);
        // 生成 @RestController 控制器
        strategyConfig.setRestControllerStyle(true);
        // 驼峰转连字符
        strategyConfig.setControllerMappingHyphenStyle(true);
        // 是否生成实体时，生成字段注解
        strategyConfig.setEntityTableFieldAnnotationEnable(true);
        // 乐观锁属性名称
        // strategyConfig.setVersionFieldName("version");
        // 逻辑删除属性名称
        // strategyConfig.setLogicDeleteFieldName("is_valid");
        strategyConfig.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategyConfig.setTablePrefix(packageConfig.getModuleName() + "_");
        return strategyConfig;
    }

    public static void main(String[] args) {
        // String projectPath = System.getProperty("user.dir") +
        // "/mybatis-plus-generator";
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(getGlobalConfig(projectPath));
        autoGenerator.setDataSource(getDataSourceConfig());
        PackageConfig packageConfig = getPackageConfig();
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setCfg(getInjectionConfig(projectPath, packageConfig));
        autoGenerator.setTemplate(getTemplateConfig());
        autoGenerator.setStrategy(getStrategyConfig(packageConfig));
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();
    }

}
