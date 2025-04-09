-- ELIMINACIÓN DE LA BASE DE DATOS
-- DROP DATABASE IF EXISTS Project_Version_01;
-- CREACIÓN DE LA BASE DE DATOS
CREATE DATABASE Project_Version_01;

USE Project_Version_01;

-- MOSTRAR TABLAS
SHOW TABLES;

SELECT
    *
FROM
    permissiontable;

SELECT
    *
FROM
    roletable;

SELECT
    *
FROM
    usertable;

SELECT
    *
FROM
    categorytable;

SELECT
    *
FROM
    brandtable;

SELECT
    *
FROM
    producttable;

SELECT
    *
FROM
    billtable;

SELECT
    *
FROM
    billdetailtable;

-- QUERY
-- SELECT ct.CategoryId, ct.CategoryName, ct.CategoryDescription, ct.CategoryState, ct.CategoryRegister, COUNT(pt.CategoryId) AS Product_Number FROM categorytable ct LEFT JOIN producttable pt ON ct.CategoryId = pt.CategoryId GROUP BY ct.CategoryId, ct.CategoryName, ct.CategoryDescription, ct.CategoryState, ct.CategoryRegister, pt.CategoryId;
-- SELECT bt.BrandId, bt.BrandName, bt.BrandPhone, bt.BrandPhone, bt.BrandAddress, bt.BrandState, bt.BrandRegister, COUNT(pt.BrandId) AS Product_Number FROM brandtable bt LEFT JOIN producttable pt ON bt.BrandId = pt.BrandId GROUP BY bt.BrandId, bt.BrandName, bt.BrandPhone, bt.BrandPhone, bt.BrandAddress, bt.BrandState, bt.BrandRegister, pt.BrandId;
-- SELECT pt.ProductId, ct.CategoryName, bt.BrandName, pt.ProductName, pt.ProductStock, pt.ProductPrice, pt.ProductDescription, pt.ProductState, pt.ProductRegister FROM producttable pt INNER JOIN categorytable ct ON pt.CategoryId = ct.CategoryId INNER JOIN brandtable bt ON pt.BrandId = bt.BrandId GROUP BY pt.ProductId, ct.CategoryName, bt.BrandName, pt.ProductName, pt.ProductStock, pt.ProductPrice, pt.ProductDescription, pt.ProductState, pt.ProductRegister;
-- SELECT bt.BillId, ut.UserName, ut.UserLastName, ut.UserAddress, ut.UserDni, bt.BillRegister, bdt.ProductQuantity, pt.ProductName, pt.ProductPrice, bdt.BillDetailSubAmount, bdt.BillDetailDiscount, bdt.BillDetailIGV, bdt.BillDetailAmount FROM billdetailtable bdt INNER JOIN billtable bt ON bdt.BillId = bt.BillId INNER JOIN usertable ut ON bt.UserId = ut.UserId INNER JOIN producttable pt ON bdt.ProductId = pt.ProductId WHERE bt.BillId = 1;
-- SELECT BillRegister, COUNT(BillRegister) AS Sell_Number FROM billtable WHERE BillRegister BETWEEN '31/03/2025' AND '31/03/2025' GROUP BY BillRegister;
-- SELECT ut.UserEmail, ut.UserPassword, rt.RoleName, pt.PermissionName FROM usertable ut INNER JOIN userrole ur ON ut.UserId = ur.UserId INNER JOIN roletable rt ON ur.RoleId = rt.RoleId INNER JOIN rolepermission rp ON rt.RoleId = rp.RoleId INNER JOIN permissiontable pt ON rp.PermissionId = pt.PermissionId;

-- LIMPIAR TABLAS
-- TRUNCATE TABLE permissiontable;
-- TRUNCATE TABLE roletable;
-- TRUNCATE TABLE usertable;
-- TRUNCATE TABLE categorytable;
-- TRUNCATE TABLE brandtable;
-- TRUNCATE TABLE producttable;
-- TRUNCATE TABLE billtable;
-- TRUNCATE TABLE billdetailtable;