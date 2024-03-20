INSERT INTO tb_client(cpf, name) VALUES ('630.456.653-06', 'Vinicius');
INSERT INTO tb_client(cpf, name) VALUES ('237.875.775-13', 'Beatriz');


INSERT INTO tb_company(cnpj, name, balance, withdrawal_fee, deposit_fee) VALUES ('77.410.614/0001-76', 'PagFacil', 110.53, 0.15, 0.10);
INSERT INTO tb_company(cnpj, name, balance, withdrawal_fee, deposit_fee) VALUES ('11.163.754/0001-89', 'SimplesCred', 55.58, 0.13, 0.08);

INSERT INTO tb_client_company(client_cpf, company_cnpj) VALUES ('630.456.653-06', '77.410.614/0001-76');
INSERT INTO tb_client_company(client_cpf, company_cnpj) VALUES ('630.456.653-06', '11.163.754/0001-89');
INSERT INTO tb_client_company(client_cpf, company_cnpj) VALUES ('237.875.775-13', '11.163.754/0001-89');