use userclass;

create table address (
                         id integer not null auto_increment,
                         city varchar(255),
                         country varchar(255),
                         street varchar(255),
                         primary key (id)
) engine=InnoDB;

    create table assignment (
                                id bigint not null auto_increment,
                                assignment_fileurl varchar(255),
                                due_date datetime(6),
                                name varchar(255),
                                text varchar(255),
                                section_id bigint,
                                primary key (id)
    ) engine=InnoDB;

    create table auditory (
                              id integer not null auto_increment,
                              name varchar(255),
                              number integer not null,
                              address_id integer,
                              primary key (id)
    ) engine=InnoDB;

    create table category (
                              id integer not null auto_increment,
                              name varchar(255),
                              primary key (id)
    ) engine=InnoDB;

    create table course (
                            id integer not null auto_increment,
                            name varchar(255),
                            price double precision not null,
                            primary key (id)
    ) engine=InnoDB;

    create table course_section (
                                    id bigint not null auto_increment,
                                    capacity integer not null,
                                    description varchar(255),
                                    end_date datetime(6),
                                    name varchar(255),
                                    photourl varchar(255),
                                    start_date datetime(6),
                                    address_id integer,
                                    course_id integer,
                                    format_id integer,
                                    primary key (id)
    ) engine=InnoDB;

    create table format (
                            id integer not null auto_increment,
                            name varchar(255),
                            primary key (id)
    ) engine=InnoDB;

    create table role (
                          id bigint not null auto_increment,
                          name varchar(255),
                          primary key (id)
    ) engine=InnoDB;

    create table sub_category (
                                  id integer not null auto_increment,
                                  name varchar(255),
                                  category_id integer,
                                  primary key (id)
    ) engine=InnoDB;

    create table sub_category_course (
                                         id integer not null auto_increment,
                                         course_id integer,
                                         sub_category_id integer,
                                         primary key (id)
    ) engine=InnoDB;

    create table user (
                          id bigint not null auto_increment,
                          description varchar(255),
                          email varchar(255),
                          first_name varchar(255),
                          last_name varchar(255),
                          password varchar(255),
                          profile_pictureurl varchar(255),
                          username varchar(255),
                          primary key (id)
    ) engine=InnoDB;

    create table user_role (
                               user_id bigint not null,
                               role_id bigint not null
    ) engine=InnoDB;

    create table user_section (
                                  id bigint not null auto_increment,
                                  course_section_id bigint,
                                  user_id bigint,
                                  primary key (id)
    ) engine=InnoDB;

    alter table user
        add constraint UKsb8bbouer5wak8vyiiy4pf2bx unique (username);

    alter table user
        add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email);

    alter table assignment
        add constraint FK5260xdyhni3trk1ykh28k4kpc
            foreign key (section_id)
                references course_section (id);

    alter table auditory
        add constraint FKq26b0b2ncrcynq5wpbdu227uk
            foreign key (address_id)
                references address (id);

    alter table course_section
        add constraint FKql7n42je16icc2i9s9o737w18
            foreign key (address_id)
                references address (id);

    alter table course_section
        add constraint FKp078rbq5ufse4i11ut9vraklr
            foreign key (course_id)
                references course (id);

    alter table course_section
        add constraint FKcj1bavb8ls3gv4wkayj6t67iu
            foreign key (format_id)
                references format (id);

    alter table sub_category
        add constraint FKl65dyy5me2ypoyj8ou1hnt64e
            foreign key (category_id)
                references category (id);

    alter table sub_category_course
        add constraint FK17c1pcyglv872y3kbf52qbb5j
            foreign key (course_id)
                references course (id);

    alter table sub_category_course
        add constraint FKig8li798o3bm7irtbq2rjbq1
            foreign key (sub_category_id)
                references sub_category (id);

    alter table user_role
        add constraint FKa68196081fvovjhkek5m97n3y
            foreign key (role_id)
                references role (id);

    alter table user_role
        add constraint FK859n2jvi8ivhui0rl0esws6o
            foreign key (user_id)
                references user (id);

    alter table user_section
        add constraint FKfkp4ofr3v8ieoedrrwlor72tf
            foreign key (course_section_id)
                references course_section (id);

    alter table user_section
        add constraint FK7v205etg91dsv6f8tv5umxema
            foreign key (user_id)
                references user (id);