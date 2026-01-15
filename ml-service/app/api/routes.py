from fastapi import APIRouter, UploadFile, File
from app.models.caption_model import caption_model
from app.models.embed_model import embed_model
from app.utils.image import load_image
from app.schemas.response import (
    ImageAnalysisResponse,
    TextEmbeddingResponse
)

router = APIRouter()

@router.post("/analyze-image", response_model=ImageAnalysisResponse)
async def analyze_image(file: UploadFile = File(...)):
    image_bytes = await file.read()
    image = load_image(image_bytes)

    caption = caption_model.generate(image)
    embedding = embed_model.embed(caption)

    return {
        "caption": caption,
        "embedding": embedding
    }

@router.post("/embed-text", response_model=TextEmbeddingResponse)
async def embed_text(text: str):
    embedding = embed_model.embed(text)
    return {"embedding": embedding}
