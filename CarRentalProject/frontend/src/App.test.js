import { render, screen } from '@testing-library/react';
import App from './App';

test('test render 1 2', () => {
  render(<App />);
  const header = screen.getByText(/WONDERLAND CAR RENTAL SYSTEM/i);
  expect(header).toBeInTheDocument();
});
