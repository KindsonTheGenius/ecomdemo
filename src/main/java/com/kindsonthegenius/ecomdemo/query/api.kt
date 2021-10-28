package com.kindsonthegenius.ecomdemo.query

import com.kindsonthegenius.ecomdemo.read_model.OrderSummary
import com.kindsonthegenius.ecomdemo.read_model.ProductSummary
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

class GetOrdersQuery
class GetProductsQuery

interface OrderSummaryRepository:JpaRepository<OrderSummary, UUID>
interface  ProductSummaryRepository:JpaRepository<ProductSummary, String>