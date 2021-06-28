package com.example.chuck_norris.categories.domain.usecase

import com.example.chuck_norris.common.Either
import com.example.chuck_norris.common.UseCase
import com.example.chuck_norris.entities.Category
import kotlinx.coroutines.flow.Flow

interface GetCategoriesUseCase : UseCase<Flow<Either<List<Category>, Exception>>, Unit>
