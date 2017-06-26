# DELIMITER $$
# CREATE
# TRIGGER update_balance_after_order
# AFTER INSERT ON orders
# FOR EACH ROW
#     BEGIN
#         SET @unitPrice = 0;
#         SET @quantity = 0;
#         SET @productId = 0;
#         SET @menuId = 0;
#
#         SET @balance = 0;
#         #### we get the actual KFET balance amount
#         SELECT balance
#         INTO @balance
#         FROM kfet
#         ORDER BY id DESC
#         LIMIT 1;
#         ####
#
#         #### We get the information of the order (the menu ID, the product ID, the quantity)
#         (SELECT
#              quantity,
#              product_id,
#              menu_id
#          INTO @quantity, @productId, @menuId
#          FROM order_product
#          WHERE order_id = NEW.id
#          LIMIT 1);
#         ####
#
#         IF @menuId IS NOT NULL AND @menuId != 0
#         THEN
#             #### The price is the price of the menu
#             (SELECT price
#              INTO @unitPrice
#              FROM menus
#              WHERE id = @menuId);
#         ####
#         ELSE
#             #### The price is the price of the product
#             (SELECT price
#              INTO @unitPrice
#              FROM products
#              WHERE id = @productId);
#         ####
#         END IF;
#
#         IF @unitPrice != 0 AND @quantity != 0
#         THEN
#             SET @newValue = @balance + @unitPrice * @quantity;
#             #we insert a new row into kfet table
#             INSERT INTO kfet
#             SET balance = @newValue;
#         END IF;
#     END $$
# DELIMITER ;