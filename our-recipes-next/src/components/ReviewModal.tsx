'use client';

import { Review } from '@/resource/recipe/review.resource';
import React, { useEffect, useState } from 'react';



interface ReviewModalProps {
  isOpen: boolean;
  onClose: () => void;
  onSubmit: (review: Review) => void; // Alterado para enviar um objeto Review
  review?: Review; // Adicionado para receber o review em caso de edição
}

export default function ReviewModal({
  isOpen,
  onClose,
  onSubmit,
  review,
}: ReviewModalProps) {
  const [comment, setComment] = useState('');
  const [rating, setRating] = useState(0);

  useEffect(() => {
    if (review) {
      setComment(review.comment || '');
      setRating(review.rating || 0);
    } else {
      setComment('');
      setRating(0);
    }
  }, [review]);

  if (!isOpen) return null;

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (rating !== null && comment.trim() !== '') {
      onSubmit({
        id: review?.id, // Inclui o ID se estiver em modo de edição
        comment,
        rating,
      });
      setComment('');
      setRating(0);
    }
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-brown-500/60">
      <div className="bg-cream p-6 rounded-lg shadow-lg w-11/12 max-w-md">
        <h2 className="text-lg font-title text-orange-600 text-center mb-4">
          {review?.id ? 'Editar Avaliação' : 'Deixe sua Avaliação'}
        </h2>
        <form onSubmit={handleSubmit}>
          {/* Campo de comentário */}
          <div className="mb-4">
            <label
              htmlFor="comment"
              className="block text-sm font-body text-brown-500 mb-1"
            >
              Comentário:
            </label>
            <textarea
              id="comment"
              name="comment"
              value={comment}
              onChange={(e) => setComment(e.target.value)}
              rows={4}
              required
              className="w-full p-2 border border-brown-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500"
            />
          </div>
          {/* Campo de rating */}
          <div className="mb-6">
            <label className="block text-sm font-body text-brown-500 mb-1">
              Avaliação:
            </label>
            <div className="flex space-x-2">
              {[1, 2, 3, 4, 5].map((star) => (
                <button
                  type="button"
                  key={star}
                  onClick={() => setRating(star)}
                  className={`text-2xl ${
                    rating >= star ? 'text-orange-500' : 'text-brown-300'
                  } hover:text-orange-600 focus:outline-none`}
                >
                  ★
                </button>
              ))}
            </div>
          </div>
          {/* Botões */}
          <div className="flex justify-end space-x-4">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 text-sm font-body text-white bg-brown-400 rounded-lg hover:bg-brown-500 focus:outline-none"
            >
              Cancelar
            </button>
            <button
              type="submit"
              className="px-4 py-2 text-sm font-body text-white bg-orange-500 rounded-lg hover:bg-orange-600 focus:outline-none"
            >
              {review?.id ? 'Salvar' : 'Enviar'}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

