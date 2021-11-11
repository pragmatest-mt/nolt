Feature: Accept submitted orders as a restaurant

  Scenario: Order is submitted by the customer and accepted by the restaurant
    Given a customer submits an order to a restaurant
    Then the customer's order is 'Submitted'
    When the restaurant accepts the order
    Then the customer's order is 'Accepted'