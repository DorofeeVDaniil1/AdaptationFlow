ALTER TABLE worker_features
    DROP CONSTRAINT fk127cv1l53dobx7sd4jivfk2tn;

ALTER TABLE worker_withdraw_coin_limits
    DROP CONSTRAINT fk27rveqkih7kxygav07du1gpl7;

ALTER TABLE worker_legal_settings
    DROP CONSTRAINT fk354fymnug58mcli7pnekemf6j;

ALTER TABLE worker_staking_plans
    DROP CONSTRAINT fk42j1npfmhu5tljx6a93ywrw8d;

ALTER TABLE worker_withdraw_coin_limits
    DROP CONSTRAINT fk61tdttya4oc99xcyosnhnp3yp;

ALTER TABLE user_deposits
    DROP CONSTRAINT fk6p42epni5dg3ooie1ilr8j9u8;

ALTER TABLE worker_domains
    DROP CONSTRAINT fk6v8ia672avo4tqdx5x8ool2pv;

ALTER TABLE supporters_support_presets
    DROP CONSTRAINT fk7372tn2c6kn7sccoa1fsxxxns;

ALTER TABLE worker_support_presets
    DROP CONSTRAINT fk8myvb8qo57cx9ka5nc05gd6r1;

ALTER TABLE workers
    DROP CONSTRAINT fkbrl3o19mtabi11s83pt2ngeob;

ALTER TABLE worker_fast_pumps
    DROP CONSTRAINT fkd8gcfskh8niuxo2mysf4se104;

ALTER TABLE worker_promocodes
    DROP CONSTRAINT fkflihfoosdwxxdhbmfh6asmj84;

ALTER TABLE worker_stable_pumps
    DROP CONSTRAINT fkfqsnuna17uq64405focc3kgtl;

ALTER TABLE user_stakings
    DROP CONSTRAINT fkgrf1dswt87kcdh5vpr1x6hs5j;

ALTER TABLE user_roles
    DROP CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6;

ALTER TABLE user_balances
    DROP CONSTRAINT fkh8somi72neg44srhywoklgyle;

ALTER TABLE worker_error_messages
    DROP CONSTRAINT fkhhuhrg65ywbl8ltru7jomwh0a;

ALTER TABLE worker_teams
    DROP CONSTRAINT fkifn9eolqwnwxxvwicp2e7o4gp;

ALTER TABLE user_alerts
    DROP CONSTRAINT fkk16e7i7kjo2mgj2mxd4ka7k5q;

ALTER TABLE worker_stable_pumps
    DROP CONSTRAINT fkljripxvyb68p3drwfh0kgrqry;

ALTER TABLE worker_fast_pumps
    DROP CONSTRAINT fkmvkgom380epdg6bofhwhbo0tm;

ALTER TABLE worker_telegram_notifications
    DROP CONSTRAINT fkn5kkx2fbem61udkcn8dki6jab;

ALTER TABLE worker_promocodes
    DROP CONSTRAINT fknijrnlqmlqwk2s9efau1t402l;

ALTER TABLE worker_record_settings
    DROP CONSTRAINT fkp7xqi0nytwe8csu0672w1kdxn;

ALTER TABLE user_deposits
    DROP CONSTRAINT fksdxap8ppeve8gd2hew0mxr18k;

ALTER TABLE workers
    DROP CONSTRAINT fkt2cjieu15n491ohe0mvklb0aa;

CREATE TABLE achievement
(
    id          UUID         NOT NULL,
    deleted_by  VARCHAR(50),
    code        VARCHAR(50)  NOT NULL,
    title       VARCHAR(150) NOT NULL,
    description TEXT,
    points      INTEGER      NOT NULL,
    CONSTRAINT pk_achievement PRIMARY KEY (id)
);

CREATE TABLE anketa
(
    id                  UUID     NOT NULL,
    user_id             UUID     NOT NULL,
    task_id             UUID     NOT NULL,
    difficulty_rating   SMALLINT NOT NULL,
    satisfaction_rating SMALLINT NOT NULL,
    CONSTRAINT pk_anketa PRIMARY KEY (id)
);

CREATE TABLE anketa_question_link
(
    anketa_id   UUID NOT NULL,
    question_id UUID NOT NULL,
    CONSTRAINT pk_anketa_question_link PRIMARY KEY (anketa_id, question_id)
);

CREATE TABLE answer_option
(
    id          UUID NOT NULL,
    question_id UUID NOT NULL,
    answer_text TEXT NOT NULL,
    CONSTRAINT pk_answer_option PRIMARY KEY (id)
);

CREATE TABLE notification
(
    id      UUID        NOT NULL,
    type    VARCHAR(50) NOT NULL,
    sent_at TIMESTAMP WITHOUT TIME ZONE,
    read    BOOLEAN     NOT NULL,
    CONSTRAINT pk_notification PRIMARY KEY (id)
);

CREATE TABLE notification_user_link
(
    notification_id UUID NOT NULL,
    user_id         UUID NOT NULL,
    CONSTRAINT pk_notification_user_link PRIMARY KEY (notification_id, user_id)
);

CREATE TABLE onboarding_track
(
    id          UUID         NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    CONSTRAINT pk_onboarding_track PRIMARY KEY (id)
);

CREATE TABLE points_rule
(
    id          UUID        NOT NULL,
    entity_type VARCHAR(20) NOT NULL,
    entity_id   UUID        NOT NULL,
    points      INTEGER     NOT NULL,
    description TEXT,
    active      BOOLEAN     NOT NULL,
    deleted_by  VARCHAR(50),
    CONSTRAINT pk_points_rule PRIMARY KEY (id)
);

CREATE TABLE points_transaction
(
    id             UUID    NOT NULL,
    user_id        UUID    NOT NULL,
    points_rule_id UUID    NOT NULL,
    anketa_id      UUID,
    points         INTEGER NOT NULL,
    CONSTRAINT pk_points_transaction PRIMARY KEY (id)
);

CREATE TABLE progress
(
    id              UUID NOT NULL,
    detail_id       UUID,
    completed_at    TIMESTAMP WITHOUT TIME ZONE,
    comment         TEXT,
    assignment_user UUID NOT NULL,
    assignment_task UUID NOT NULL,
    CONSTRAINT pk_progress PRIMARY KEY (id)
);

CREATE TABLE question_option
(
    id            UUID    NOT NULL,
    question_text TEXT    NOT NULL,
    is_active     BOOLEAN NOT NULL,
    deleted_by    VARCHAR(50),
    CONSTRAINT pk_question_option PRIMARY KEY (id)
);

CREATE TABLE role
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    deleted_by  VARCHAR(50),
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE support_request
(
    id          UUID        NOT NULL,
    user_id     UUID        NOT NULL,
    question    TEXT        NOT NULL,
    answer      TEXT,
    status      VARCHAR(20) NOT NULL,
    answered_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_support_request PRIMARY KEY (id)
);

CREATE TABLE sys_users
(
    id              UUID         NOT NULL,
    login           VARCHAR(100) NOT NULL,
    password_hash   VARCHAR(255) NOT NULL,
    first_name      VARCHAR(50),
    last_name       VARCHAR(50),
    patronymic      VARCHAR(100),
    email           VARCHAR(254),
    is_active       BOOLEAN      NOT NULL,
    last_login_date TIMESTAMP WITHOUT TIME ZONE,
    user_level_id   UUID,
    CONSTRAINT pk_sys_users PRIMARY KEY (id)
);

CREATE TABLE task_detail
(
    id          UUID         NOT NULL,
    task_id     UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    status      VARCHAR(50)  NOT NULL,
    deleted_by  VARCHAR(50),
    CONSTRAINT pk_task_detail PRIMARY KEY (id)
);

CREATE TABLE task_detail_additional_info
(
    id             UUID NOT NULL,
    task_detail_id UUID NOT NULL,
    document_url   TEXT NOT NULL,
    category       TEXT NOT NULL,
    CONSTRAINT pk_task_detail_additional_info PRIMARY KEY (id)
);

CREATE TABLE tasks
(
    id                UUID         NOT NULL,
    track_stage_id    UUID         NOT NULL,
    name              VARCHAR(255) NOT NULL,
    description       TEXT,
    status            VARCHAR(50)  NOT NULL,
    expired_time_task TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_tasks PRIMARY KEY (id)
);

CREATE TABLE track_stage
(
    id                  UUID         NOT NULL,
    onboarding_track_id UUID         NOT NULL,
    title               VARCHAR(255) NOT NULL,
    description         TEXT,
    order_index         INTEGER      NOT NULL,
    CONSTRAINT pk_track_stage PRIMARY KEY (id)
);

CREATE TABLE user_achievement_link
(
    awarded_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_id        UUID                        NOT NULL,
    achievement_id UUID                        NOT NULL,
    CONSTRAINT pk_user_achievement_link PRIMARY KEY (user_id, achievement_id)
);

CREATE TABLE user_level
(
    id          UUID         NOT NULL,
    title       VARCHAR(100) NOT NULL,
    min_points  INTEGER      NOT NULL,
    max_points  INTEGER,
    description TEXT,
    deleted_by  VARCHAR(50),
    CONSTRAINT pk_user_level PRIMARY KEY (id)
);

CREATE TABLE user_onboarding_track_link
(
    id         UUID                        NOT NULL,
    user_id    UUID                        NOT NULL,
    track_id   UUID                        NOT NULL,
    started_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status     VARCHAR(20)                 NOT NULL,
    create_ts  TIMESTAMP WITHOUT TIME ZONE,
    created_by VARCHAR(50),
    update_ts  TIMESTAMP WITHOUT TIME ZONE,
    updated_by VARCHAR(50),
    delete_ts  TIMESTAMP WITHOUT TIME ZONE,
    deleted_by VARCHAR(50),
    CONSTRAINT pk_user_onboarding_track_link PRIMARY KEY (id)
);

CREATE TABLE user_role_link
(
    role_id UUID NOT NULL,
    user_id UUID NOT NULL,
    CONSTRAINT pk_user_role_link PRIMARY KEY (role_id, user_id)
);

CREATE TABLE user_track_stage_link
(
    id           UUID        NOT NULL,
    uot_id       UUID        NOT NULL,
    stage_id     UUID        NOT NULL,
    status       VARCHAR(20) NOT NULL,
    unlocked_at  TIMESTAMP WITHOUT TIME ZONE,
    completed_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_user_track_stage_link PRIMARY KEY (id)
);

CREATE TABLE users_tasks_link
(
    assigned_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    status      VARCHAR(20)                 NOT NULL,
    due_date    TIMESTAMP WITHOUT TIME ZONE,
    user_id     UUID                        NOT NULL,
    task_id     UUID                        NOT NULL,
    CONSTRAINT pk_users_tasks_link PRIMARY KEY (user_id, task_id)
);

CREATE UNIQUE INDEX achievement_achievement_code_key ON achievement (code);

CREATE UNIQUE INDEX points_rule_uq_points_rule ON points_rule (entity_type, entity_id);

CREATE UNIQUE INDEX role_role_name_key ON role (name);

CREATE UNIQUE INDEX track_stage_uq_ts_order ON track_stage (onboarding_track_id, order_index);

CREATE UNIQUE INDEX user_level_user_level_title_key ON user_level (title);

CREATE UNIQUE INDEX user_onboarding_track_link_uq_uot ON user_onboarding_track_link (user_id, track_id);

CREATE UNIQUE INDEX user_track_stage_uq_uts ON user_track_stage_link (uot_id, stage_id);

ALTER TABLE anketa
    ADD CONSTRAINT FK_ANKETA_ON_TASK FOREIGN KEY (task_id) REFERENCES tasks (id);

ALTER TABLE anketa
    ADD CONSTRAINT FK_ANKETA_ON_USER FOREIGN KEY (user_id) REFERENCES sys_users (id);

ALTER TABLE answer_option
    ADD CONSTRAINT FK_ANSWER_OPTION_ON_QUESTION FOREIGN KEY (question_id) REFERENCES question_option (id);

ALTER TABLE points_transaction
    ADD CONSTRAINT FK_POINTS_TRANSACTION_ON_ANKETA FOREIGN KEY (anketa_id) REFERENCES anketa (id);

ALTER TABLE points_transaction
    ADD CONSTRAINT FK_POINTS_TRANSACTION_ON_POINTS_RULE FOREIGN KEY (points_rule_id) REFERENCES points_rule (id);

ALTER TABLE points_transaction
    ADD CONSTRAINT FK_POINTS_TRANSACTION_ON_USER FOREIGN KEY (user_id) REFERENCES sys_users (id);

CREATE INDEX points_transaction_idx_points_transaction_user_id ON points_transaction (user_id);

ALTER TABLE progress
    ADD CONSTRAINT FK_PROGRESS_ON_DETAIL FOREIGN KEY (detail_id) REFERENCES task_detail (id);

ALTER TABLE support_request
    ADD CONSTRAINT FK_SUPPORT_REQUEST_ON_USER FOREIGN KEY (user_id) REFERENCES sys_users (id);

ALTER TABLE sys_users
    ADD CONSTRAINT FK_SYS_USERS_ON_USER_LEVEL FOREIGN KEY (user_level_id) REFERENCES user_level (id);

ALTER TABLE tasks
    ADD CONSTRAINT FK_TASKS_ON_TRACK_STAGE FOREIGN KEY (track_stage_id) REFERENCES track_stage (id);

CREATE INDEX tasks_idx_task_stage_id ON tasks (track_stage_id);

ALTER TABLE task_detail_additional_info
    ADD CONSTRAINT FK_TASK_DETAIL_ADDITIONAL_INFO_ON_TASK_DETAIL FOREIGN KEY (task_detail_id) REFERENCES task_detail (id);

ALTER TABLE task_detail
    ADD CONSTRAINT FK_TASK_DETAIL_ON_TASK FOREIGN KEY (task_id) REFERENCES tasks (id);

ALTER TABLE track_stage
    ADD CONSTRAINT FK_TRACK_STAGE_ON_ONBOARDING_TRACK FOREIGN KEY (onboarding_track_id) REFERENCES onboarding_track (id);

ALTER TABLE users_tasks_link
    ADD CONSTRAINT FK_USERS_TASKS_LINK_ON_TASK FOREIGN KEY (task_id) REFERENCES tasks (id);

CREATE INDEX users_tasks_link_idx_users_tasks_link_task_id ON users_tasks_link (task_id);

ALTER TABLE users_tasks_link
    ADD CONSTRAINT FK_USERS_TASKS_LINK_ON_USER FOREIGN KEY (user_id) REFERENCES sys_users (id);

CREATE INDEX users_tasks_link_idx_users_tasks_link_user_id ON users_tasks_link (user_id);

ALTER TABLE user_achievement_link
    ADD CONSTRAINT FK_USER_ACHIEVEMENT_LINK_ON_ACHIEVEMENT FOREIGN KEY (achievement_id) REFERENCES achievement (id);

ALTER TABLE user_achievement_link
    ADD CONSTRAINT FK_USER_ACHIEVEMENT_LINK_ON_USER FOREIGN KEY (user_id) REFERENCES sys_users (id);

ALTER TABLE user_onboarding_track_link
    ADD CONSTRAINT FK_USER_ONBOARDING_TRACK_LINK_ON_TRACK FOREIGN KEY (track_id) REFERENCES onboarding_track (id);

ALTER TABLE user_onboarding_track_link
    ADD CONSTRAINT FK_USER_ONBOARDING_TRACK_LINK_ON_USER FOREIGN KEY (user_id) REFERENCES sys_users (id);

ALTER TABLE user_track_stage_link
    ADD CONSTRAINT FK_USER_TRACK_STAGE_LINK_ON_STAGE FOREIGN KEY (stage_id) REFERENCES track_stage (id);

ALTER TABLE user_track_stage_link
    ADD CONSTRAINT FK_USER_TRACK_STAGE_LINK_ON_UOT FOREIGN KEY (uot_id) REFERENCES user_onboarding_track_link (id);

CREATE INDEX user_track_stage_idx_user_track_stage_uot_id ON user_track_stage_link (uot_id);

ALTER TABLE anketa_question_link
    ADD CONSTRAINT fk_ankque_on_anketa FOREIGN KEY (anketa_id) REFERENCES anketa (id);

ALTER TABLE anketa_question_link
    ADD CONSTRAINT fk_ankque_on_question_option FOREIGN KEY (question_id) REFERENCES question_option (id);

ALTER TABLE notification_user_link
    ADD CONSTRAINT fk_notuse_on_notification FOREIGN KEY (notification_id) REFERENCES notification (id);

ALTER TABLE notification_user_link
    ADD CONSTRAINT fk_notuse_on_sys_user FOREIGN KEY (user_id) REFERENCES sys_users (id);

ALTER TABLE user_role_link
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES role (id);

ALTER TABLE user_role_link
    ADD CONSTRAINT fk_userol_on_sys_user FOREIGN KEY (user_id) REFERENCES sys_users (id);

DROP TABLE admin_email_settings CASCADE;

DROP TABLE admin_error_messages CASCADE;

DROP TABLE admin_features CASCADE;

DROP TABLE admin_legal_settings CASCADE;

DROP TABLE admin_staking_plans CASCADE;

DROP TABLE admin_support_presets CASCADE;

DROP TABLE admin_telegram_ids CASCADE;

DROP TABLE admin_telegram_notifications CASCADE;

DROP TABLE admin_telegram_settings CASCADE;

DROP TABLE coins CASCADE;

DROP TABLE email_bans CASCADE;

DROP TABLE p2p_fakes CASCADE;

DROP TABLE payment_settings CASCADE;

DROP TABLE roles CASCADE;

DROP TABLE supporters CASCADE;

DROP TABLE supporters_support_presets CASCADE;

DROP TABLE telegram_messages CASCADE;

DROP TABLE user_addresses CASCADE;

DROP TABLE user_alerts CASCADE;

DROP TABLE user_api_keys CASCADE;

DROP TABLE user_balances CASCADE;

DROP TABLE user_deposits CASCADE;

DROP TABLE user_email_confirms CASCADE;

DROP TABLE user_error_messages CASCADE;

DROP TABLE user_features CASCADE;

DROP TABLE user_futures_orders CASCADE;

DROP TABLE user_futures_positions CASCADE;

DROP TABLE user_kyc CASCADE;

DROP TABLE user_logs CASCADE;

DROP TABLE user_required_deposit_coins CASCADE;

DROP TABLE user_roles CASCADE;

DROP TABLE user_stakings CASCADE;

DROP TABLE user_support_dialogs CASCADE;

DROP TABLE user_support_messages CASCADE;

DROP TABLE user_trade_orders CASCADE;

DROP TABLE user_transactions CASCADE;

DROP TABLE user_wallet_connects CASCADE;

DROP TABLE worker_domains CASCADE;

DROP TABLE worker_error_messages CASCADE;

DROP TABLE worker_fast_pumps CASCADE;

DROP TABLE worker_features CASCADE;

DROP TABLE worker_legal_settings CASCADE;

DROP TABLE worker_promocodes CASCADE;

DROP TABLE worker_record_settings CASCADE;

DROP TABLE worker_stable_pumps CASCADE;

DROP TABLE worker_staking_plans CASCADE;

DROP TABLE worker_support_presets CASCADE;

DROP TABLE worker_teams CASCADE;

DROP TABLE worker_telegram_notifications CASCADE;

DROP TABLE worker_withdraw_coin_limits CASCADE;

DROP TABLE workers CASCADE;