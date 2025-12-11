-- 주문 테이블
create table purchase_order (
    order_id binary(16) default (uuid_to_bin(uuid())) not null comment '주문번호',
    name varchar(255) not null comment '주문자명',
    phone_number varchar(255) not null comment '주문자 휴대전화번호',
    order_state varchar(255) not null comment '주문상태',
    payment_id varchar(255) null comment '결제정보',
    total_price int not null comment '상품 가격 * 주문 수량',
    created_at datetime default now() not null,
    updated_at datetime default now() not null,
    primary key (order_id)
);

-- 주문 상세 테이블
create table order_items (
    id int not null comment '주문 상세 ID' auto_increment,
    order_id binary(16) not null comment '주문번호',
    item_idx integer(10) not null comment '주문 상세번호',
    product_id binary(16) not null comment '상품번호',
    product_name varchar(255) not null comment '상품명',
    product_price int not null comment '상품 가격',
    product_size varchar(255) not null comment '상품 사이즈',
    quantity int not null comment '주문 수량',
    amount int not null comment '총 가격 (상품가격 * 주문 수량)',
    order_state varchar(255) comment '개별 주문상태',
    created_at datetime default now() not null,
    updated_at datetime default now() not null,
    primary key (id),
    unique key(order_id, item_idx, product_id)
);

-- 거래원장 테이블
create table payment_transaction (
   id int not null comment '거래 ID' auto_increment,
   payment_id varchar(255) not null comment '거래 번호(ID)',
   method varchar(255) not null comment '거래 수단',
   payment_status varchar(255) not null comment '거래 상태',
   total_amount int not null comment '최종 결제 금액 (즉시 할인 금액 포함)',
   balance_amount int not null comment '취소 가능한 금액(잔고)',
   canceled_amount int not null comment '취소된 총 금액',
   created_at datetime default now() not null,
   updated_at datetime default now() not null,
   primary key (id),
   unique key(payment_id, method, payment_status)
);

-- 거래 수단 정보 테이블
create table card_payment (
    payment_id varchar(255) not null comment '결제번호(payment id)',
    card_number varchar(255) not null comment '카드번호',
    approve_no varchar(10) not null comment '카드 승인 번호',
    acquire_status varchar(255) not null comment '카드결제 매입 상태', -- 가맹점이 최종 청구 요청 → 실제로 돈이 나가는 단계
    issuer_code varchar(255) null comment '카드 발급사 코드',
    acquirer_code varchar(255) not null comment '카드 매입사 코드',
    acquirer_status varchar(255) not null comment '카드 결제의 상태', -- 일단 결제 OK인지 확인만 한 단계
    primary key (payment_id),
    unique key(payment_id, card_number, approve_no)
)


